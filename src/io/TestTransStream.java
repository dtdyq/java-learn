package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	InputStreamReader   OutputStreamWriter
 *		java������ת���������ڽ��ֽ���ת��Ϊ�ַ���������ָ�����뷽ʽ�����Ը���������ڴ���
 *		һ��Ϊ���ԣ�
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
