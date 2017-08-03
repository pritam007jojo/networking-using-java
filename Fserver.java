import java.net.*;
import java.io.*;

public class Fserver
{
	public static void main(String args[]) throws Exception
	{
		ServerSocket serverSocket = null;
		Socket echoSocket = null;
		BufferedReader in= null;
		int flag=1;
		try{
			serverSocket = new ServerSocket(95);
			echoSocket = new Socket(InetAddress.getByName("localhost"),96);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		}
		catch(Exception e)
		{}
		Socket clientSocket = null;
		try{
		clientSocket  = serverSocket.accept();
		System.out.println("Connnected to"+ clientSocket);
		}
		catch(Exception e)
		{}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
		BufferedReader stdin =new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			if(flag==1)//send data
			{
			System.out.println("enter data from server");
			String s=stdin.readLine();
			if(s==null||s.equals("end"))
			break;
			out.write(s);
			out.write("\n");
			out.flush();
			flag=0;
			}
			else if(flag==0)//receive data
			{
				try{
						String userInput;
						userInput=in.readLine();
						System.out.println("Message from client " + userInput);
						if(userInput.equals("end")||(userInput==null))
						{
							flag=99;
							break;
						}
				}catch(Exception e){}
				flag=1;
			}
		}
		out.close();
		clientSocket.close();
		serverSocket.close();
		in.close();
		echoSocket.close();
	}
}