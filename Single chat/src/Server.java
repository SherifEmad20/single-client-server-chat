import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
	public static void main(String[] args)
	{
		try
		{
			ServerSocket serversocket = new ServerSocket(4000);
			System.out.println("Server is booted up and waiting for any client to connect");
			while (true)
				{
				Socket clientSocket = serversocket.accept();
				System.out.println("A new client has connected");
				
				DataInputStream input = new DataInputStream(clientSocket.getInputStream());	
				DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
				Scanner scanner = new Scanner(System.in);
				
				output.writeUTF("Connected");
				while (true)
				{
					output.writeUTF("Send the request or 'close' to close connection");
					
					String request = input.readUTF();
					System.out.println("Client: " + request);
					
					if (request.equalsIgnoreCase("close"))
					{
						System.out.println("will close connection");
						clientSocket.close();
						System.out.println("the connection is closed");
						break;
					}			
					
					String reply = scanner.nextLine();
					output.writeUTF(reply);
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Problem in connection with port number");

		}
	}
}
