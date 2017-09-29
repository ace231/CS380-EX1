/*****************************************
*	Alfredo Ceballos
*	CS 380 - Computer Networks
*	Exercise 1
*	Professor Nima Davarpanah
*****************************************/

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
		
		// Initiallize server socket
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
			
			// Infinite loop
            while (true) {
				// Accept connections
				Socket socket = serverSocket.accept();
				
				/*
				 *	Creating Runnable thread for server. 
				 * 	Thread begins right after an EchoClient server process establishes
				 *	a connection with the server, meaning multiple EchoClient processes and
				 *	have independent threads dedicated to handling them.
				 */
				Runnable echoServerThread = () -> {
					try {
						// Save client's address
						String address = socket.getInetAddress().getHostAddress();
						System.out.printf("Client connected: %s%n", address);
						
						// Create outputstream and printstream to send messages to clients
						OutputStream os = socket.getOutputStream();
						PrintStream out = new PrintStream(os, true, "UTF-8");
						
						// Inputstream and bufferedreader to receive clients' messages
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is, "UTF-8");
						BufferedReader clientBr = new BufferedReader(isr);
						
						/*
						 * This while loop will continously wait for input from the client
						 * coming in through the inputstream's bufferedreader. Once it receives
						 * something it will send it back. If the input is "exit" then the loop
						 * breaks and the socket is closed right after.
						 */
						while(true) {
							String clientMsg = clientBr.readLine();	// Save client message
							out.printf("Server> %s%n",clientMsg);	// Send message back
							//System.out.println(clientMsg);	// Checking message
							if(clientMsg.equals("exit")){
								break;
							}
						}
						
						socket.close();
						System.out.println("socket closed");
					}
					catch(IOException e){
							System.out.println("A client has disconnected...");
					}
				};	// Ending of echoServerThread
		    
				// A thread runs after the server accepts a connection
				new Thread(echoServerThread).start();
			}	//Ending or loop
			
        }
		
    }
	
}
