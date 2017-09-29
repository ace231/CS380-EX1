import java.io.OutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		String msg = "";
		while(true){
			try (Socket socket = new Socket("localhost", 22222)) {
					
				String address = socket.getInetAddress().getHostAddress();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				System.out.println(br.readLine());
				
				OutputStream os = socket.getOutputStream();
				PrintStream out = new PrintStream(os, true, "UTF-8");
				//out.printf("Hi server! This is %s", address);
				msg = kb.readLine();
				if(msg.toLowerCase().equals("exit")){
					System.exit(0);
				}
				//System.out.printf("Sending %s%n",msg);//delete
				out.printf("%s",msg);
				
			}
		}
    }
}















