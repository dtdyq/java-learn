package socket;

import java.net.*;
import java.util.*;
class UdpRece 
{
	public static void main(String[] args)throws Exception{
		@SuppressWarnings("resource")
		DatagramSocket ds=new DatagramSocket(10001);
		while(true){
			byte[] buf=new byte[1024];
			DatagramPacket dp=new DatagramPacket(buf,buf.length);
			ds.receive(dp);
			String ip=dp.getAddress().getHostAddress();
			String data=new String(dp.getData());
			System.out.println(ip+"::"+data);
		}
	}
}
class UdpSend 
{
	public static void main(String[] args)throws Exception{
		DatagramSocket ds=new DatagramSocket();
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		String str;
		while((str=sc.nextLine())!=null){
			if(str.equals("over")){
				break;
			}
			byte[] buf=str.getBytes();
			DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),10001);
			ds.send(dp);
		}
		ds.close();
	}
}