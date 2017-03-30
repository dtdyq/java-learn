package socket;

import java.io.*;
import java.net.*;
class ServerTcp{
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("resource")
		ServerSocket ss=new ServerSocket(10000);
		Socket s=ss.accept();
		InputStream is=s.getInputStream();

		@SuppressWarnings("resource")
		FileOutputStream fos=new FileOutputStream(new File("file/socket/test.jpg"));

		byte[] buf=new byte[1024];
		int len;
		while((len=is.read(buf))!=-1){
			fos.write(buf,0,len);
		}
	}
}
class ClientTcp{
	public static void main(String[] args)throws Exception{
		@SuppressWarnings("resource")
		Socket s=new Socket("127.0.0.1",10000);
		OutputStream os=s.getOutputStream();

		FileInputStream fis=new FileInputStream(new File("file/socket/1.jpg"));

		byte[] buf=new byte[1024];
		int len;
		while((len=fis.read(buf))!=-1){
			os.write(buf,0,len);
		}

		fis.close();
		s.shutdownOutput();
	}
}

