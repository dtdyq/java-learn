package socket;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
public class TestURLs{
	public static void main(String[] args){
		try {
			URL url=new URL("Http://www.sina.com");
			System.out.println(url.getProtocol());
			System.out.println(url.getHost());
			System.out.println(url.getFile());
			System.out.println(url.getPath());
			System.out.println(url.getQuery());
			URLConnection uc=null;
			try {
				uc = url.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(uc);
			System.out.println("================");
			InputStream is=null;
			try {
				is = uc.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] buf=new byte[1024*64*1024];
			int len=0;
			try {
				len = is.read(buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(new String(buf,0,len));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
