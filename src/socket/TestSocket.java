package socket;
import java.io.*;
import java.net.*;

/**
 * Created by Admin on 2017/3/26.
 * 使用TCP进行网络连接：
 *
 * 服务器：ServerSocket
 * 客户端：Socket
 */
public class TestSocket {
	public static void main(String[] args) {
	}
}
class SocketServer {

	public static void main(String[] args) {

		InputStream is;
		OutputStream os;
		try{
			ServerSocket ss=new ServerSocket(10001);
			Socket s=ss.accept();
			is=s.getInputStream();
			os=s.getOutputStream();
			DataOutputStream dos=new DataOutputStream(os);
			DataInputStream dis=new DataInputStream(is);
			String str;
			if((str=dis.readUTF())!=null){
				System.out.println(str);
				System.out.println("from:"+s.getInetAddress());
				System.out.println("port:"+s.getPort());
			}
			dos.writeUTF("hi,everyone");
			dis.close();
			dos.close();
			s.close();
			ss.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
class SocketClient {

	public static void main(String[] args) {
		
		InputStream in;
		OutputStream out;
		try{
			Socket socket=new Socket("localhost",10001);
			//设置超时时长，
			socket.setSoTimeout(10000);
			in=socket.getInputStream();
			out=socket.getOutputStream();
			DataInputStream dis=new DataInputStream(in);
			DataOutputStream dos=new DataOutputStream(out);
			dos.writeUTF("hello");
			String s=null;
			if((s=dis.readUTF())!=null){
				System.out.println(s);
				
			}
			dos.close();
			dis.close();
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
