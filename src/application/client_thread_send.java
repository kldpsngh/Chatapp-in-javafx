package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class client_thread_send{
	
	
	private static Socket clientSocket;
	private static DataInputStream is;
	private static PrintStream os;
	//private static BufferedReader inputLine;
	
	
	public client_thread_send(Socket clientSocket,DataInputStream is,PrintStream os) {
		
		this.clientSocket=clientSocket;
		this.is=is;
		this.os=os;
	}
	
	public void send(String text)
	{
		if(clientSocket!=null && os!=null && is!=null)
		{
			  text=encryption_decryption.encryptData(text);
				os.println(text);
		}
	}
	

}
