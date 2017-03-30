package io;
import java.io.*;
public class TestFile {
	//File类的常用方法和使用方法：
	public static void FileS() throws IOException{
		File file=new File("file/io/FileTest.txt");
		//获取文件名;
		System.out.println(file.getName());
		//获取文件绝对路径：
		System.out.println(file.getAbsoluteFile());
		System.out.println(file.getAbsolutePath());
		//获取文件的父路径：可能为null:
		System.out.println(file.getParent());
		
		//在当前路径下创建一个临时文件：
		File file2=File.createTempFile("testtemp", ".txt",new File("file/io/"));
		//指定当JVM退出时删除该文件;
		file2.deleteOnExit();
		//Thread.sleep(5000);
		
		//以系统当前时间创建新文件：
		File newfile=new File("file/"+System.currentTimeMillis()+".txt");
		//判断文件是否存在：
		if(!newfile.exists()){
			newfile.createNewFile();
		}
		
		//创建目录：
		File newpath=new File("file/testpath");
		//判断目录是否存在：
		if(!newpath.exists()){
			newpath.mkdir();
		}
		
		System.out.println("列出目录下所有文件和路径：");
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
		
		System.out.println("列出系统根目录：");
		File[] root=File.listRoots();
		for(File f:root){
			System.out.println(f);
		}
	}
	//打印出所给文件路径下的所有文件和文件夹
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











