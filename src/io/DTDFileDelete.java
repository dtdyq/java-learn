package io;
import java.io.*;
import java.util.*;
/**
 * 
 * @author dtdyq
 *	删除指定文件夹下的所有文件：
 */
public class DTDFileDelete {
	public static void fileDelete(String filename){
		fileDelte(new File(filename));
	}
	public static void fileDelte(File file){
		long t=System.currentTimeMillis();
		if(file.isFile()){
			file.delete();
		}
		LinkedList<File> queue=new LinkedList<File>();
		queue.offer(file);
		while(!queue.isEmpty()){
			File f=queue.poll();
			File[] files=f.listFiles();
			
			for(File ff:files){
				if(ff.isDirectory()){
					queue.offer(ff);
				}
				else{
					ff.delete();
				}
			}
			f.delete();
		}
		file.delete();
		System.out.println("delete successful,\nusing time: "+
				(System.currentTimeMillis()-t)+ " ms"
				);
	}
	public static void main(String[] args){
		//测试删除指定文件夹下的所有文件和文件夹：
		DTDFileDelete.fileDelete("C:\\myeclipse\\plugins\\");

	}
}
