package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	对应于InputStream，字节输出流为：OutputStream
 *	对应于Reader   字符输出流为：Writer
 *		常用的写入操作：
 *			write(int c)--->写入一个字节
 *			write(byte[] buff/char[] buff)--->写入一个字符数组
 *			write(byte[] buff/char[] buff,int off,int len)--->将字节数组的固定位置和长度的数据写入输出流
 *			write(String str)--->写入字符串
 *			write(String str,int off,int len)--->写入字符串中的固定位置字符
 *	以下为OutputStream/Writer的测试：
 */
public class TestOutput {
	
	public static void main(String[] args){
		OutputStreamTest.writeTest(new File("file/io/Output.txt"));
		WriteTest.writeTest(new File("file/io/Output.txt"));
	}
}
class OutputStreamTest{
	public static void writeTest(File file){
		OutputStream os=null;
		try{
			os=new FileOutputStream(file);
			int c=122;
			String str="writetest--OutputStream";
			os.write(c);
			os.write(str.getBytes());
			System.out.println("write successful");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
class WriteTest{
	public static void writeTest(File file){
		Writer os=null;
		try{
			os=new FileWriter(file);
			int c=122;
			String str="writetest--Writer";
			os.write(c);
			os.write(str);
			System.out.println("write successful");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
