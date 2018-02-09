package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Main_application_controller implements Initializable {
	
	
	@FXML
	private Button dp_button;
	
	@FXML
	private Button connect_button;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private TextField name_field;
	
	@FXML
	private Label dp_path;
	
	public static String name="";
	public Stage stage;
	public Parent root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//name_field.requestFocus();
	}
	
	@FXML
	private void connect (ActionEvent event) throws Exception
	{
		if(event.getSource()==connect_button)
		{
			name=name_field.getText();
			stage=(Stage)connect_button.getScene().getWindow();
			root=FXMLLoader.load(getClass().getResource("UI.fxml"));
			Scene scene=new Scene(root,700,155);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
					
		}
	}
	
	@FXML private void connect_via_button(KeyEvent event) throws Exception
	{
		if(event.getCode()==KeyCode.ENTER)
		{
			name=name_field.getText();
			stage=(Stage)connect_button.getScene().getWindow();
			root=FXMLLoader.load(getClass().getResource("UI.fxml"));
			Scene scene=new Scene(root,700,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
	}
	
}
