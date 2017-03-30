package io;
import java.io.*;
public class TestFile {
	//File��ĳ��÷�����ʹ�÷�����
	public static void FileS() throws IOException{
		File file=new File("file/io/FileTest.txt");
		//��ȡ�ļ���;
		System.out.println(file.getName());
		//��ȡ�ļ�����·����
		System.out.println(file.getAbsoluteFile());
		System.out.println(file.getAbsolutePath());
		//��ȡ�ļ��ĸ�·��������Ϊnull:
		System.out.println(file.getParent());
		
		//�ڵ�ǰ·���´���һ����ʱ�ļ���
		File file2=File.createTempFile("testtemp", ".txt",new File("file/io/"));
		//ָ����JVM�˳�ʱɾ�����ļ�;
		file2.deleteOnExit();
		//Thread.sleep(5000);
		
		//��ϵͳ��ǰʱ�䴴�����ļ���
		File newfile=new File("file/"+System.currentTimeMillis()+".txt");
		//�ж��ļ��Ƿ���ڣ�
		if(!newfile.exists()){
			newfile.createNewFile();
		}
		
		//����Ŀ¼��
		File newpath=new File("file/testpath");
		//�ж�Ŀ¼�Ƿ���ڣ�
		if(!newpath.exists()){
			newpath.mkdir();
		}
		
		System.out.println("�г�Ŀ¼�������ļ���·����");
		System.out.println("frist---->");
		File paths=new File("file");
		String[] files=paths.list();
		for(String str:files){
			System.out.println(str);
		}
		System.out.println("second---->");
		File[] filess=paths.listFiles();
		for(File f:filess){
			System.out.println(f);
		}
		
		System.out.println("�г�ϵͳ��Ŀ¼��");
		File[] root=File.listRoots();
		for(File f:root){
			System.out.println(f);
		}
	}
	//��ӡ�������ļ�·���µ������ļ����ļ���
	public static void FilePrint(File file) throws IOException{
		Print(file,"  ");
	}
	private static void Print(File file,String str){
		
		if(file.isDirectory()){
			System.out.println(str+file.getName());
			File[] files=file.listFiles();
			for(File f:files){
				if(f.isDirectory()){
					Print(f,str.concat(str));
				}
				else{
					System.out.println(str.concat(str)+f.getName());
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		FilePrint(new File("file/"));
		
	}
}











