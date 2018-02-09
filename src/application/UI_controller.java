package application;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Clock;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class UI_controller implements Initializable {
	
	   
	   @FXML private  Label user_name;
	   
	   @FXML private TextField text_field;
	   
	   @FXML public TextArea convo_area;
	   
	   @FXML private Button send_button;
	   
	   @FXML private Button leave_chat;
	   
	   @FXML private ListView display_online_name;
	   
	   @FXML private Button download_button;
	   
	   
	   protected ListProperty<String> listproperty=new SimpleListProperty<>();
	   
	   
	   
	   private int portNumber;
	   private String host;
	   public Socket clientSocket;
	   public DataInputStream is;
	   public PrintStream os;
	   public client_thread_send send_thread_obj;
	   
	   
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		user_name.setText(Main_application_controller.name);
		text_field.requestFocus();
		portNumber=22234;
		host="localhost";
		//convo_area.setText("          welcome to our chat room");
		
		/*try to connect to server*/
		try {
		      clientSocket = new Socket(host, portNumber);
		      os = new PrintStream(clientSocket.getOutputStream());
		      is = new DataInputStream(clientSocket.getInputStream());
		      
		      /*clientSocket.isClosed()==true*/
		      
		    } catch (UnknownHostException e) {
		      System.err.println("Don't know about host " + host);
		    } catch (IOException e) {
		      System.err.println("Couldn't get I/O for the connection to the host "
		          + host);
		    }catch(Exception e)
			{
		      System.err.println(e);	
			}
		
		/*client thread for sending messages*/
		
		try {
			if(is.readLine()==null)
			  {
				  server_too_busy t=new server_too_busy();
				  try {
					t.display();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		send_thread_obj=new client_thread_send(clientSocket, is, os);
		 
		display_online_name.itemsProperty().bind(listproperty);
		 /*client thread for receiving messages*/
		 new Thread(new client_thread_receive(clientSocket,is,os,convo_area,listproperty)).start();
		 
		 //sending first the name of the current client
		 send_thread_obj.send(Main_application_controller.name);
		 
	}
	
	
	
	@FXML
	 private Button attach_button;
	
	@FXML
	private void Attach_action(ActionEvent event) throws Exception
	{
		if(event.getSource()==attach_button)
		{
			mediator.th=send_thread_obj;
			UI_Attach_popup p=new UI_Attach_popup();
			p.display_pop_up();
			
		}
	}	
	
	public String get_text_from()
	{
		return text_field.getText();
	}
	
	public void set_text_field_to_null()
	{
		text_field.clear();
	}
	
	
	/*this event handler will send data to client send_thread*/
	@FXML
	private void send_message(ActionEvent event) throws Exception
	{
		if(event.getSource()==send_button)
		{
			String msg=get_text_from();
			set_text_field_to_null();
			send_thread_obj.send(msg);
		}
		
	}


	
	@FXML private void keyListener(KeyEvent event) throws Exception
	{
		if(event.getCode()==KeyCode.ENTER)
		{
			String msg=get_text_from();
			if(msg==null)
				event.consume();   //but it's not working
			set_text_field_to_null();
			send_thread_obj.send(msg);
		}
	}
	
	@FXML private void Disconnect_client(ActionEvent event) throws Exception
	{
		if(event.getSource()==leave_chat)
		{
			send_thread_obj.send("$$$$$$");  /*here connection closes*/
			disconnect_window w=new disconnect_window();
			w.display();
		}
	}
	
	@FXML private void Download_Action(ActionEvent event) throws Exception
	{
		if(event.getSource()==download_button)
		{ 
			URI u=new URI("ftp://192.168.43.176:21/");
			java.awt.Desktop.getDesktop().browse(u);
		}
	}
	
  
}
