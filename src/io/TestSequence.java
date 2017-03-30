package io;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;
/**
 * 
 * @author dtdyq
 * SequenceInputStream:
 * 合并流：将多个输入流依次读入
 */
public class TestSequence {
	public static void main(String[] args) throws IOException{
		Vector<FileInputStream> v=new Vector<FileInputStream>();
		v.add(new FileInputStream("file/io/1.txt"));
		v.add(new FileInputStream("file/io/2.txt"));
		v.add(new FileInputStream("file/io/3.txt"));
		Enumeration<FileInputStream> e=v.elements();
		SequenceInputStream si=new SequenceInputStream(e);
		byte[] buff=new byte[1024];
		int len=-1;
		while((len=si.read(buff))!=-1){
			System.out.println(new String(buff,0,len));
		}
		si.close();
	}
}
