package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	InputStream:
 *		常用读取方法：
 *			int read()-->从输入流中读入一个字节：
 *			int read(byte[] buff)-->从输入流中最多读入buff.length长度的字节，并保存在buff中
 *			int read(byte[] buff,int off,int len)-->最多读取len长的字节存入buff从off起始的位置
 *		一下是三种读取方式的演示：
 */
public class TestInputStream {
	//单个字节读取：
	public static void readTest(File file){
		if(!file.exists()){
			System.out.println("file is not exist.");
			return;
		}
		if(file.isDirectory()){
			System.out.println("file is directory.");
			return;
		}
		FileInputStream is=null;
		try{
			is=new FileInputStream(file);
			int b=0;
			while((b=is.read())!=-1){
				System.out.print(Character.toChars(b));
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try{
				is.close();
			}catch(IOException ee){
				System.out.println("error");
			}
		}
	}
	//读取一定的字节入缓冲区：
	public static void readBuffTest(File file){
		if(!file.exists()){
			System.out.println("file is not exist.");
			return;
		}
		if(file.isDirectory()){
			System.out.println("file is directory.");
			return;
		}
		InputStream is=null;
		try{
			is=new FileInputStream(file);
			int len=-1;
			byte[] buff=new byte[1024];
			while((len=is.read(buff))!=-1){
				System.out.print(new String(buff,0,len));
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] agrs){
		readTest(new File("file/io/FileTest.txt"));
		System.out.println("\n=================");
		readBuffTest(new File("file/io/FileTest.txt"));
	}
}
