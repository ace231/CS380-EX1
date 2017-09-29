/*****************************************
*	Alfredo Ceballos
*	CS 380 - Computer Networks
*	Exercise 1
*	Professor Nima Davarpanah
*****************************************/

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
		// BufferedReader for keyboard
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		String msg = "";	// Used to hold input
		
		//  Initialize new socket and connect with server
		Socket socket = new Socket("localhost", 22222);
		
		/*
		 * Everytime a new instance of the EchoClient program is created,
		 * that instance only needs to create input and output streams once
		 * rather than everytime the following while loop begins.
		 */
		 
		//Creating input and output streams for the client
		//String address = socket.getInetAddress().getHostAddress();
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
						
		OutputStream os = socket.getOutputStream();
		PrintStream out = new PrintStream(os, true, "UTF-8");
		
		while(true){
			try {
				System.out.print("Client> ");
				msg = kb.readLine();
				
				if(msg.toLowerCase().equals("exit")){
					System.exit(0);
				}
				
				out.printf("%s\n",msg);
				System.out.println(br.readLine());
			}
			catch(Exception e){
				System.out.println("Something went wrong...");
			}
		}// Ending of while loop
		
    }
	
}















