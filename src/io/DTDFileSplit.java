package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	实现文件问个功能，将指定文件分割为指定长度的子文件：
 *	用RandomAccessFile实现(可进行读写操作)
 *	分割后的文件已系统时间加系列号为文件名(分割后的文件可能已损坏)
 */
public class DTDFileSplit {
	private RandomAccessFile src;
	private String fileType;
	public DTDFileSplit(File file){
		try {
			
			if(file.isDirectory()){
				System.out.println("the given file is a directory");
				System.exit(0);
			}
			fileType=file.getName().split("\\.")[1];
			this.src=new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public DTDFileSplit(String file){
		this(new File(file));
	}
	//将分割后的文件写入指定目录file中 ，文件长度为length(byte)：
	public void fileSplit(String file,int length){
		fileSplit(new File(file),length);
	}
	public void fileSplit(File file,int length){
		long t=System.currentTimeMillis();
		if(file.isFile()){
			System.out.println("the given file is not a directory.");
			return;
		}
		if(length<=0){
			System.out.println("the given length is illegal.");
			return;
		}
		split(file,length);
		System.out.println("file split successful.\nusing time: "+
				(System.currentTimeMillis()-t)+" ms"
				);
	}
	//分割文件：
	private void split(File file,int length){
		try {
			long fileLen=src.length();
			long size=fileLen/length;
			BufferedOutputStream bos;
			
			for(int i=0;i<=size;i++){
				
				File f=new File(file.getAbsolutePath()+"/"+
						System.currentTimeMillis()+"--"+i+"."+fileType);
				bos=new BufferedOutputStream(
						new FileOutputStream(f)
						);
				writeTo(bos,i,length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//将src中指定位置开始的指定长度的字节数写入指定输出流
	private void writeTo(BufferedOutputStream file,int i,int length){
		byte[] buff=new byte[length];
		int len=-1;
		try {
			src.seek(i*length);
			len=src.read(buff);
			file.write(buff,0,len);
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//将指定文件夹下的相同文件合并为一个文件：
	public static void fileMerge(String file,String dest){
		fileMerge(new File(file),new File(dest));
	}
	public static void fileMerge(File file,File dest){
		if(!file.exists()||file.isFile()||dest.isDirectory()){
			System.out.println("source file or destination is illagl");
			return;
		}
		File[] files=file.listFiles();
		BufferedOutputStream bos=null;
		try {
			bos=new BufferedOutputStream(
					new FileOutputStream(dest,true)
					);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(File f:files){
			System.out.println("Meging file--->"+f.getName());
			writeTo(f,bos);
		}
	}
	public static void writeTo(File src,BufferedOutputStream dest){
		try {
			BufferedInputStream bis=new BufferedInputStream(
					new FileInputStream(src)
					);
			int len=-1;
			byte[] buff=new byte[1024*1024];
			while((len=bis.read(buff))!=-1){
				dest.write(buff,0,len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		//分割文件：
		DTDFileSplit dfs=new DTDFileSplit("file/io/testpath/SplitTest.pdf");
		dfs.fileSplit("file/io/testpath/", 1024*100);
		//合并文件：
		DTDFileSplit.fileMerge("file/io/testpath/", "file/io/testpath/SplitTest.pdf");
	}
}








