import java.net.*;
import java.io.*;

class DatagramClient
{
	public static DatagramSocket ds;
	public static int clientport = 789,serverport=790;
	public static void main(String args[]) throws Exception
	{
		byte buffer1[]= new byte[1024];
		byte buffer2[]= new byte[1024];
		int flag=0;
		InetAddress ia = InetAddress.getLocalHost();
		ds= new DatagramSocket(clientport);
		while(true)
		{
			if(flag==0)//Receive data
			{
				DatagramPacket p=new DatagramPacket(buffer1,buffer1.length);
				ds.receive(p);
				String psx= new String(p.getData(),0,p.getLength());
				System.out.println(psx);
				if(psx.equals("Logoff & GoodBye .."))
				break;
				psx= null;
				flag=1;
			}
			else if(flag==1)//Send Data
			{
				System.out.println("Your Response ..");
				BufferedReader entry= new BufferedReader(new InputStreamReader(System.in));
				String str= entry.readLine();
				if(str==null||str.equals("end"))
				{
					str="Logoff & GoodBye ..";
					flag=99;
				}
				buffer2 = str.getBytes();
				ds.send(new DatagramPacket(buffer2,str.length(),ia,serverport));
				if(flag==99)
				break;
				flag=0;
			}
		}
	}
}