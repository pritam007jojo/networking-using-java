import java.net.*;
import java.io.*;
class DatagramServer
{
	public static DatagramSocket ds;
	public static byte buffer1[]=new byte[1024];
	public static byte buffer2[]=new byte[1024];
	public static int clientport= 789,serverport= 790;
	public static void  main(String args[])throws Exception
	{
		int flag=1;
		InetAddress ia = InetAddress.getLocalHost();
		BufferedReader dis=new BufferedReader(new InputStreamReader(System.in));
		ds= new DatagramSocket(serverport);
		while(true)
		{
			if(flag==1)//Send data
			{
				System.out.println("Your Response ..");
				String str=dis.readLine();
				if((str==null)||(str.equals("end")))
				{
					str="Logoff & Goodbye ..";
					flag=99;
				}
				buffer1=str.getBytes();
				ds.send(new DatagramPacket(buffer1,str.length(),ia,clientport));
				if(flag==99)
					break;
					flag=0;	
			}
			else if(flag==0)//Receive Data
			{
				DatagramPacket p=new DatagramPacket(buffer2,buffer2.length);
				ds.receive(p);
				String str1=new String(p.getData(),0,p.getLength());
				System.out.println(str1);
				if(str1.equals("Logoff & Goodbye .."))
				break;
				flag=1;
			}
		}	
	}
}