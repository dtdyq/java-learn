package socket;
import java.net.*;
import java.io.*;
/**
 * 
 * @author dtdyq
 *ģ��ͻ��˲����ϴ�ͼƬ
 */
public class DTDParallUpload {
	public static void main(String[] args){

	}

}
class PicServer{
	public static void main(String[] args){
		ServerSocket ss=null;
		try {
			ss=new ServerSocket(10011);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				Socket s = ss.accept();
				//����һ��ͼƬ�����߳���������ͼƬ
				new Thread(new PicThread(s)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
class PicThread implements Runnable{
	private static int count=1;
	private Socket s=null;
	public PicThread(Socket s){
		this.s=s;
	}
	@Override
	public void run() {
		FileOutputStream fos=null;
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip+"...connected");
		try {
			fos=new FileOutputStream(new File("file/socket/"+(count++)+".jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream is=null;
		try{
			is=s.getInputStream();
		}catch(IOException e){
			e.printStackTrace();
		}
		byte[] buf=new byte[1024];
		int len;
		try{
			while((len=is.read(buf))!=-1){
				fos.write(buf, 0, len);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
			s.close();
			fos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
}
class PicClient{
	public static void main(String[] args){
		Socket s=null;
		
		try {
			s=new Socket("127.0.0.1",10011);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ȡͼƬ��������
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(new File("file/socket/TestURL2.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//��ȡsocket�������
		OutputStream os=null;
		try {
			os=s.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf=new byte[1024];
		int len;
		try {
			while((len=fis.read(buf))!=-1){
				os.write(buf, 0, len);
			}
			s.shutdownOutput();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				s.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}










