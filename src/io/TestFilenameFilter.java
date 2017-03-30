package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	�ļ���������
 *		FilenameFilter��
 *			�г�ֻ�����������ļ����ļ��У�
 */
public class TestFilenameFilter {
	static void test(){
		File f=new File("file/io/");
		String[] list=f.list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
			
		});
		for(String str:list){
			System.out.println(str);
		}
	}
	public static void main(String[] args){
		test();
	}
}
