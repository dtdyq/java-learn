package socket;
import java.io.*;
import java.net.*;
//使用ServerSocket和Socket模拟客户端服务器之间的http请求响应
public class BrowserDemo {
	public static void main(String[] args){

	}
	
}
class ServerHttp{
	public static void main(String[] agrs) throws Exception{
		ServerSocket ss=new ServerSocket(10011);
		Socket s=ss.accept();
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip);
		InputStream is=s.getInputStream();
		byte[] b=new byte[1024*1024];
		int len=is.read(b);
		System.out.println(new String(b,0,len));
 		PrintWriter out =new PrintWriter(s.getOutputStream(),true);
		out.println("welcome");
		s.close();
		ss.close();
	}
}
class ClientHttp{
	public static void main(String[] args)throws Exception{
		@SuppressWarnings("resource")
		Socket s=new Socket("127.0.0.1",10011);
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		out.println("GET / HTTP/1.1");
		out.println("Host: 127.0.0.1:10000");
		out.println("Connection: keep-alive");
		out.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		out.println("Accept-Language: zh-CN,zh;q=0.8");
		out.println();
		InputStream is=s.getInputStream();
		byte[] b=new byte[1024];
		int len=is.read(b);
		System.out.println(new String(b,0,len));
	}
}









