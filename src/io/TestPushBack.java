package io;
/**
 * 
 * @author dtdyq
 * PushbackInputStream  PushbackReader：
 * 		unRead(byte[]/char[])
 * 		unRead(byte[]/char[],off,len)
 * 		unRead(int b)
 * 	
 *推回输入流：可以将指定的字节或字符数重新推入输入缓冲区中，以允许对其重复读取
 *	以下为测试：
 *		当读到class TestPushBack时停止读取，并将这些字节重新写入流中
 */
import java.io.PushbackReader;
import java.io.FileReader;
import java.io.IOException;
public class TestPushBack {
	public static void main(String[] args){
		PushbackReader pbr=null;
		try{
			pbr=new PushbackReader(new FileReader("file/io/TestPushBack.txt"),64);
			char[] buf=new char[32];
			String lastContent="";
			int hasRead=0;
			while((hasRead=pbr.read(buf))>0){
				String content=new String(buf,0,hasRead);
				int targetIndex=0;
				if((targetIndex=(lastContent+content).indexOf("class TestPushBack"))>0){
					pbr.unread((lastContent+content).toCharArray());
					if(targetIndex>32){
						buf=new char[targetIndex+1024];
					}
					pbr.read(buf,0,targetIndex);
					System.out.println(new String(buf,0,targetIndex));
					System.exit(0);
				}
				else{
					System.out.print(lastContent);
					lastContent=content;
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try {
				pbr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
