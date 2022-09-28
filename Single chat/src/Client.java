import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client
{

	public static void main(String[] args)
	{
		try
		{
			InetAddress IP = InetAddress.getLocalHost();
			Socket ClientSocket = new Socket(IP , 4000);
			DataInputStream input = new DataInputStream(ClientSocket.getInputStream());	
			DataOutputStream output = new DataOutputStream(ClientSocket.getOutputStream());
			Scanner scanner = new Scanner(System.in);

			String confirmedmessage = input.readUTF();
			System.out.println("Server: " + confirmedmessage);
			while (true)
			{
				String askmessage = input.readUTF();
				System.out.println("Server: " + askmessage);
				
				String request = scanner.nextLine();
				output.writeUTF(request);
				
				if (request.equalsIgnoreCase("close"))
				{
					System.out.println("will close connection");
					ClientSocket.close();
					System.out.println("the connection is closed");	
					break;
				}
				
				String reply = input.readUTF();
				System.out.println("Server: " + reply);
			}
		}
		catch (IOException e)
		{
			System.out.println ("Problem in IP address");
		}
	}

}
