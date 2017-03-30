package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	LineNumberReader:可以用来带行号输出和设置行号：
 *	以下为其测试
 *  LineNumberInputStream(该类的方法都已过时)和该类功能相同  这两个类只有输入流，没有对应的输出流
 */
public class TestLineNumber {
	public static void main(String[] args){
		try {
			LineNumberReader lr=new LineNumberReader(
					new FileReader("file/io/FileTest.txt"));
			//设置行号：
			/*
			lr.setLineNumber(99);
			System.out.println(lr.read());
			System.out.println(lr.read());
			System.out.println(lr.read());
			System.out.println(lr.readLine());
			
			char[] ch=new char[8];
			int len=-1;
			while((len=lr.read(ch))!=-1){
				System.out.println(lr.getLineNumber()+"-->"+new String(ch,0,len));
			}
			 */
		    String line=null;
			while((line=lr.readLine())!=null){
				//获取行号并打印该行：
				System.out.println(lr.getLineNumber()+"-->"+line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
