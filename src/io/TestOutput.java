package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	��Ӧ��InputStream���ֽ������Ϊ��OutputStream
 *	��Ӧ��Reader   �ַ������Ϊ��Writer
 *		���õ�д�������
 *			write(int c)--->д��һ���ֽ�
 *			write(byte[] buff/char[] buff)--->д��һ���ַ�����
 *			write(byte[] buff/char[] buff,int off,int len)--->���ֽ�����Ĺ̶�λ�úͳ��ȵ�����д�������
 *			write(String str)--->д���ַ���
 *			write(String str,int off,int len)--->д���ַ����еĹ̶�λ���ַ�
 *	����ΪOutputStream/Writer�Ĳ��ԣ�
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
