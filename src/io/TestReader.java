package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 
 * @author dtdyq
 *	字符输入流：Reader：
 *		（底层节点流）和InputStream类似，共有一下三种读取方式
 *			read()--->读取单个字符
 *			read(char[] buff)--->读取一定长度的字符数组进buff中
 *			read(char[] buff, int off,int len)--->读取len长度的字符数组进入buff中，以off为起始位置
 */
public class TestReader {
	//逐个字符的读取：
	public static void readTest(File file){
		if(!file.exists()){
			System.out.println("file is not exist.");
			return;
		}
		if(file.isDirectory()){
			System.out.println("file is directory.");
			return;
		}
		Reader r=null;
		try{
			r=new FileReader(file);
			int b;
			while((b=r.read())!=-1){
				System.out.print(Character.toChars(b));
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				r.close();
			}catch(IOException ee){
				System.out.println(ee);
			}
		}
	}
	//用缓冲区进行读取：
	public static void readBuffTest(File file){
		if(!file.exists()){
			System.out.println("file is not exist.");
			return;
		}
		if(file.isDirectory()){
			System.out.println("file is directory.");
			return;
		}
		Reader r=null;
		try{
			r=new FileReader(file);
			int len=-1;
			char[] buff=new char[1024];
			while((len=r.read(buff))!=-1){
				System.out.print(String.copyValueOf(buff, 0, len));
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try{
				r.close();
			}catch(IOException ee){
				System.out.println(ee);
			}
		}
	}
	public static void main(String[] args){
		readTest(new File("file/io/Input.txt"));
		System.out.println("\n====================");
		readBuffTest(new File("file/io/Input.txt"));
	}
}
