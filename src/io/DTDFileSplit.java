package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	ʵ���ļ��ʸ����ܣ���ָ���ļ��ָ�Ϊָ�����ȵ����ļ���
 *	��RandomAccessFileʵ��(�ɽ��ж�д����)
 *	�ָ����ļ���ϵͳʱ���ϵ�к�Ϊ�ļ���(�ָ����ļ���������)
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
	//���ָ����ļ�д��ָ��Ŀ¼file�� ���ļ�����Ϊlength(byte)��
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
	//�ָ��ļ���
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
	//��src��ָ��λ�ÿ�ʼ��ָ�����ȵ��ֽ���д��ָ�������
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
	//��ָ���ļ����µ���ͬ�ļ��ϲ�Ϊһ���ļ���
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
		//�ָ��ļ���
		DTDFileSplit dfs=new DTDFileSplit("file/io/testpath/SplitTest.pdf");
		dfs.fileSplit("file/io/testpath/", 1024*100);
		//�ϲ��ļ���
		DTDFileSplit.fileMerge("file/io/testpath/", "file/io/testpath/SplitTest.pdf");
	}
}








