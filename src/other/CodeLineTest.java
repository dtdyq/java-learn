package other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CodeLineTest {
	/**
	 * 统计指定目录下所有java文件个数：
	 * @param args
	 */
	public static List<String> fileCount(String name){
		File f=new File(name);
		List<String> list=new ArrayList<>();
		int count=0;
		ArrayDeque<File> queue=new ArrayDeque<>();
		queue.addLast(f);
		while(!queue.isEmpty()){
			File file=queue.removeFirst();
			if(file.isDirectory()){
				for(File fs:file.listFiles()){
					queue.addLast(fs);
				}
			}
			if(file.isFile()){
				if(file.getName().endsWith(".java")){
					count++;
					list.add(file.getAbsolutePath());
				}
			}
		}
		System.out.println(count);
		list.forEach((str)->{
			System.out.println(str);
		});
		return list;
	}
	public static void lineCount(String name) throws IOException{
		List<String> list=fileCount(name);
		int count=0;
		File file;
		Iterator<String> it=list.iterator();
		BufferedReader br;
		while(it.hasNext()){
			br=new BufferedReader(new FileReader(it.next()));
			while(br.readLine()!=null){
				count++;
			}
			br=null;
		}
		FileWriter pw=new FileWriter("file/other/lines.txt",true);
		pw.write(name+"="+count);
		pw.write("\r\n");
		pw.close();
	}
	public static void main(String[] args) throws Exception{
		Properties pop=new Properties();
		pop.load(new FileInputStream("file/other/lines.txt"));
		int count=0;
		for(String str:pop.stringPropertyNames()){
			count+=Integer.parseInt(pop.getProperty(str));
		}
		System.out.println(count);
		/**
		 * 2017-3-0  :  34215
		 */
	}
}









