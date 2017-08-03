import java.net.*;
import java.io.*;

public class Fclient
{
	public static void main(String args[]) throws Exception
	{
		Socket echoSocket = null;
		BufferedReader in=null;
		ServerSocket serverSocket = null;
		int flag=0;
		try
		{
			echoSocket = new Socket(InetAddress.getByName("localhost"),95);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			serverSocket = new ServerSocket(96);
		}
		catch(Exception e){}
		Socket clientSocket = null;
		try{
		clientSocket  = serverSocket.accept();
		System.out.println("Connnected to"+ clientSocket);
		}
		catch(Exception e)
		{}
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
		BufferedReader stdin =new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		while(true)
		{
			if(flag==0) // receive data
			{
				try{
					userInput=in.readLine();
					System.out.println("Message from server " + userInput);
					if(userInput.equals("end")||(userInput==null))
					{
						flag=99;
						break;
					}
				}catch(Exception e){}
				flag=1;
			}
			else if(flag==1)//send data
			{
				System.out.println("enter data from client");
				String s= stdin.readLine();
				if(s==null||s.equals("end"))
				{
					flag=99;
					break;
				}
				out.write(s);
				out.write("\n");
				out.flush();
				flag=0;
			}
		}
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
		echoSocket.close();
		
	}
}