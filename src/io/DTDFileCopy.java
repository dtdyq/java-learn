package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *  该类用于复制指定文件夹下的文件到指定目录：
 *  
 */
public class DTDFileCopy {
	public static void copy(String src,String dest){
		copy(new File(src),new File(dest));
	}
	public static void copy(File src,File dest){
		long t=System.currentTimeMillis();
		//判断源路径或目标路径是否存在：
		if(!src.exists()||!dest.exists()){
			System.out.println("source or dest directory or file is not exist!");
			return;
		}
		//源路径是否可读：
		if(!src.canRead()){
			System.out.println("source file or directory cannot read!");
			return;
		}
		//目的路径不能被写入：
		if(!dest.canWrite()){
			System.out.println("dest directory cannot write!");
			return;
		}
		//判断目标路径是否是文件:
		if(dest.isFile()){
			System.out.println("dest is not a directory!");
			return;
		}
		copFile(src,dest);
		System.out.println("copy success!");
		System.out.println("using time: "+(System.currentTimeMillis()-t)+" ms");
	}
	//实现文件批量复制的功能：
	public static void copFile(File src,File dest){
		if(src.isDirectory()){
			new File(dest.getAbsolutePath()+"/"+src.getName()).mkdir();
			File[] file=src.listFiles();
			for(File f:file){
				copFile(f,new File(dest.getAbsolutePath()+"/"+src.getName()));
			}
		}
		else{
			fileWrite(src,new File(dest.getAbsolutePath()+"/"+src.getName()));
		}
	}
	//将单个文件复制到指定文件夹下：
	private static void fileWrite(File src,File dest){
		try{
			BufferedInputStream bis=new BufferedInputStream(
					new FileInputStream(src)
					);
			BufferedOutputStream bos=new BufferedOutputStream(
					new FileOutputStream(dest)
					);
			int len=-1;
			byte[] buff=new byte[1024*64];
			while((len=bis.read(buff))!=-1){
				bos.write(buff, 0, len);
				bos.flush();
			}
			bis.close();
			bos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		DTDFileCopy.copy("D:/myeclipse/JAVA_CODE/file/io/copyTest", 
				"D:/myeclipse/JAVA_CODE/file/io/copyTest1");

	}
}
