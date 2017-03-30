package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *  BufferedReader��BufferedWriter/BufferedInputStream��BUfferedOutputStream��
 *  	���ĸ����������ַ�/�ֽڻ�����������������߶�дЧ��
 *  	ÿ�ο��Զ�ȡ��д��һ��
 */
public class TestBuffered {
	//�ַ���������
	public static void bufferedRTest(File file){
		try{
			BufferedReader br=new BufferedReader(
					new FileReader(file)
					);
			String line=null;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//�ַ��������
	public static void bufferedWTest(File file){
		try{
			BufferedWriter bw=new BufferedWriter(
					new FileWriter(file,true)
					);
			bw.write("test of BufferedWriter");
			bw.flush();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		TestBuffered.bufferedWTest(new File("file/io/BufferedTest.txt"));
		TestBuffered.bufferedRTest(new File("file/io/BufferedTest.txt"));
	}
}
