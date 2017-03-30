package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	InputStream:
 *		���ö�ȡ������
 *			int read()-->���������ж���һ���ֽڣ�
 *			int read(byte[] buff)-->����������������buff.length���ȵ��ֽڣ���������buff��
 *			int read(byte[] buff,int off,int len)-->����ȡlen�����ֽڴ���buff��off��ʼ��λ��
 *		һ�������ֶ�ȡ��ʽ����ʾ��
 */
public class TestInputStream {
	//�����ֽڶ�ȡ��
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
	//��ȡһ�����ֽ��뻺������
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
