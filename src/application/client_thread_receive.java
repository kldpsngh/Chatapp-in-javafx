package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class client_thread_receive implements Runnable {
	
	
	private Socket clientSocket;
	private DataInputStream is;
	private PrintStream os;
	private TextArea convo_area;
	private ObservableList<String> online_client;
	protected ListProperty<String> listproperty;
	
	 public client_thread_receive(Socket clientSocket,DataInputStream is,PrintStream os,TextArea convo_area,ListProperty<String> listproperty) 
	 {
		
		 this.clientSocket=clientSocket;
		 this.is=is;
		 this.os=os;
		 this.convo_area=convo_area;
		 this.listproperty=listproperty;
	 }

	 /*this thread receives data sent by the server and writes in client console*/
	@Override
	public void run() {
		
		System.out.println("\nclient thread run\n");
		if(clientSocket!=null && os!=null && is!=null &&clientSocket.isClosed()==false)
		{
			String responseLine;
			try{
				    synchronized (this) {
					while((responseLine=is.readLine())!=null)
					{
						responseLine=encryption_decryption.decryptData(responseLine);
						System.out.println("response line---->"+responseLine);
						String test[]=responseLine.split(" ");
						if(test[0].equals("$$$***$$$")||test[0].equals("***$$$***"))
						{	
							online_client=FXCollections.observableArrayList();
							for(int i=1;i<test.length;i++) {
								online_client.add(test[i]);	
								System.out.println(test[i]);
		
							}
							System.out.println("-->");
							try {
								listproperty.set(online_client);	
							}catch(Exception e)
							{
								System.out.println("hurray");
							}
							
						}
						else
						{
							System.out.println(responseLine+"\n");
							convo_area.appendText(responseLine+"\n");
						}	
					}
					}
			}catch(IOException e)
			{
				System.out.println(e);
			}
		}
}
}
