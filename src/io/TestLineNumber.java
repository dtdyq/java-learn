package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	LineNumberReader:�����������к�����������кţ�
 *	����Ϊ�����
 *  LineNumberInputStream(����ķ������ѹ�ʱ)�͸��๦����ͬ  ��������ֻ����������û�ж�Ӧ�������
 */
public class TestLineNumber {
	public static void main(String[] args){
		try {
			LineNumberReader lr=new LineNumberReader(
					new FileReader("file/io/FileTest.txt"));
			//�����кţ�
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
				//��ȡ�кŲ���ӡ���У�
				System.out.println(lr.getLineNumber()+"-->"+line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
