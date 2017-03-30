package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *  BufferedReader、BufferedWriter/BufferedInputStream、BUfferedOutputStream：
 *  	这四个流对象都是字符/字节缓冲流，可以显著提高读写效率
 *  	每次可以读取或写入一行
 */
public class TestBuffered {
	//字符输入流：
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
	//字符输出流：
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
