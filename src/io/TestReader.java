package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 
 * @author dtdyq
 *	�ַ���������Reader��
 *		���ײ�ڵ�������InputStream���ƣ�����һ�����ֶ�ȡ��ʽ
 *			read()--->��ȡ�����ַ�
 *			read(char[] buff)--->��ȡһ�����ȵ��ַ������buff��
 *			read(char[] buff, int off,int len)--->��ȡlen���ȵ��ַ��������buff�У���offΪ��ʼλ��
 */
public class TestReader {
	//����ַ��Ķ�ȡ��
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
	//�û��������ж�ȡ��
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
