package application;


import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class server {
	
	private static ServerSocket serversocket=null;
	private static Socket clientSocket=null;
	private static final int max_clients_count=3;
	private static final clientThread threads[]=new clientThread[max_clients_count];
	public  static boolean close_application;
	public static ArrayList<String> client_names;
	
	public static void main(String[] args) {
		
		
		// The default port number.
	    int portNumber = 22234;
	    if (args.length < 1) {
	      System.out.println("Usage: java MultiThreadChatServerSync <portNumber>\n"
	          + "Now using port number=" + portNumber);
	    } else {
	      portNumber = Integer.valueOf(args[0]).intValue();
	    }
	    
	    /*create a server socket*/
	    try{
	       serversocket=new ServerSocket(portNumber);
	       client_names=new ArrayList<>();
	       }catch(IOException e)
	    {
	    	System.out.println(e);
	    }
	    
	     /*
	     * Create a client socket for each connection and pass it to a new client
	     * thread.
	     */
	    while(true)
	    {
	    	try{
	    		clientSocket=serversocket.accept();
	    		System.out.println("Client accepted");
	    		int i=0;
	    		for(i=0;i<max_clients_count;i++)
	    		{
	    			if(threads[i]==null)
	    			{
	    				(threads[i]=new clientThread(clientSocket,threads)).start();
	    				
	    				break;
	    			}
	    		}
	    		
	    		if(i==max_clients_count)
	    		{
	    	     	clientSocket.close();
	    	     	
	    		}
	    		
	    	}catch(IOException e)
	    	{
	    		System.err.println(e);
	    	}
	    }
	}
	

}

/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. The thread broadcast the incoming messages to all clients and
 * routes the private message to the particular client. When a client leaves the
 * chat room this thread informs also all the clients about that and terminates.
 */
class clientThread extends Thread
{
	  private DataInputStream is = null;
	  private PrintStream os = null;
	  private Socket clientSocket = null;
	  private final clientThread[] threads;
	  private int maxClientsCount;
	  private boolean called=false;
	  public String name;
	
	
	
	public clientThread(Socket clientSocket, clientThread[] threads)
	{
	    this.clientSocket = clientSocket;
	    this.threads = threads;
	    maxClientsCount = threads.length;
	  }
	
	public void run()
	{
		int maxClientsCount = this.maxClientsCount;
		System.out.println("server_run ");
	    clientThread[] threads = this.threads;
	    
	    try{
	    	
	    	is=new DataInputStream(clientSocket.getInputStream());
	    	os=new PrintStream(clientSocket.getOutputStream());
	    	
	    	
	    	
	    	/*welcome the new client*/	
    		os.println("welcome to our chat Room!!");
	    	
	    	while((name=is.readLine())==null)
	    	{
	    		
	    	}
	    	called=true;
	    	name=encryption_decryption.decryptData(name);
	    	server.client_names.add(name);
	   
	    	/*notify other clients about this new one*/
	    		for(int i=0;i<maxClientsCount;i++)
	    		{
	    			if(threads[i]!=null && threads[i]!=this)
	    			{
	    				threads[i].os.println(encryption_decryption.encryptData("        New user connected!!---> "+name));
	    				
	    			}
	    		}
	    		
	    		String s1="";
	    		for(int i=0;i<server.client_names.size();i++)
	    		{
	    			s1=s1+" "+server.client_names.get(i);
	    		
	    		}
	    		
	    		    		
	    			for(int i=0;i<maxClientsCount;i++)
		    		{
		    			if(threads[i]!=null)
		    			{
		    				String text="$$$***$$$"+s1;
		    				text=encryption_decryption.encryptData(text);
		    				threads[i].os.println(text);   //initialize list signal
		    			}
		    		}
	    		
	
	    		while(true&&called)
	    		{
	    			/*read from server*/
	    			String line=is.readLine();
	    			System.out.println(line);
	    			line=encryption_decryption.decryptData(line);
	    			System.out.println("line---->"+line);
	    			if(line.equals("$$$$$$"))
	    				break;
	    			
	    			/*send this to every client including this one*/
	    			
	    		  if(line.charAt(0)=='@')   //message is private		
	    		   {
	    				synchronized (this) {
	    					
	    					String private_message[]=line.split(" ");
    						String name_of_the_private_person=private_message[0].substring(1, private_message[0].length());
    						String message=line.substring(private_message[0].length()+1);
	    					
	    					for(int i=0;i<maxClientsCount;i++)
	    					{
	    						if(threads[i]!=null&&(threads[i].name.equals(name_of_the_private_person)==true||threads[i]==this))
	    						{
	    							String text=name+"->"+name_of_the_private_person+" : "+message;
	    							text=encryption_decryption.encryptData(text);
	    							threads[i].os.println(text);
	    						}
	    					}
							
						}
	    		   }
	    		  else                    //message is public 
	    		  {
	    			synchronized (this)
	    				{
	    				for(int i=0;i<maxClientsCount;i++)
	    				{
	    					if(threads[i]!=null)
	    					{
	    						String text=name+" : "+line;
	    						text=encryption_decryption.encryptData(text);
	    						threads[i].os.println(text);
	    					}
	    				}
	    				}
	    		  }
	    		}
	    		
	    		
	    		
	    		for(int i=0;i<server.client_names.size();i++)
    		    {
    		    	if(server.client_names.get(i).equals(name)==true)
    		    	{
    		    		server.client_names.remove(i);
    		    		break;
    		    	}
    		    }
    		    
    		    String s2="";
    		    for(int i=0;i<server.client_names.size();i++)
    		    {
    		    	s2=s2+" "+server.client_names.get(i);
    		    }
	    		
	    		synchronized(this)
	    		{
	    		    
	    			for(int i=0;i<maxClientsCount;i++)
	    			{
	    				if(threads[i]!=null&&threads[i]!=this)
	    				{
	    					threads[i].os.println(encryption_decryption.encryptData("       "+name+" Disconnected from chat"));
	    					threads[i].os.println(encryption_decryption.encryptData("***$$$***"+s2));    //list change signal
	    				}
	    			}
	    			
	    		
	    		for(int i=0;i<maxClientsCount;i++)
	    		{
	    			if(threads[i]!=null&&threads[i]==this)
	    			{
	    				threads[i]=null;
	    				break;
	    			}
	    		}
	    		
	    		}
	    	
	    	/*close connection*/
	    	is.close();
	    	os.close();
	    	clientSocket.close();
	    	
	    }catch(IOException e)
	    {
	    	System.err.println(e);
	    }
		
		
	}
	
}