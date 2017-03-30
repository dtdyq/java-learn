package socket;

import java.io.*;
import java.net.*;
class TcpClient
{
	public static void main(String[] args)throws Exception{
		Socket s=new Socket("127.0.0.1",10003);
		OutputStream os=s.getOutputStream();
		os.write("test".getBytes());
		s.close();
	}
}
class TcpServer
{
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("resource")
		ServerSocket ss=new ServerSocket(10003);
		Socket s=ss.accept();
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip);
		InputStream is=s.getInputStream();
		byte[] b=new byte[1024];
		int len=is.read(b);
		System.out.println(new String(b,0,len));
	}
}