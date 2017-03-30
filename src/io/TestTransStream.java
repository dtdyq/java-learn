package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	InputStreamReader   OutputStreamWriter
 *		java的两个转换流：用于将字节流转换为字符流，可以指定编码方式，可以更方便地用于处理
 *		一下为测试：
 */
public class TestTransStream {
	public static void main(String[] args) throws IOException{
		System.out.println("InputStreamReader Test:");
		BufferedReader br=new BufferedReader(
				new InputStreamReader(System.in)
				);
		String line=null;
		while(!(line=br.readLine()).equals("over")){
			System.out.println("text: "+line);
		}
		br.close();
	}
}
