package io;
import java.io.*;
import java.util.Scanner;
/**
 * 
 * @author dtdyq
 *	Runtime对象的exec()可以用于运行本机上其他的程序
 *	返回一个Process对象，是java程序启动的子进程
 *	共有一下三种方法，可以用于java程序和子进程之间通信;
 *		getErrorStream()   获取子进程的错误流
 *		getOutputStream()  获取子进程的输出流
 *		getInputStream     获取子进程的输入流
 */
public class TestProcess {
	//子进程输出错误流：
	public static void PError(){
		try {
			Process process=Runtime.getRuntime().exec("javac");
			BufferedReader br=new BufferedReader(
					new InputStreamReader(process.getErrorStream())
					);
			String line=null;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void POutput(){
		try{
			Process pro=Runtime.getRuntime().exec(
					"java io/PTest");
			//获取pro的输出流:
			PrintStream pw=new PrintStream(pro.getOutputStream());
			pw.println("output test");
			pw.println(new TestProcess());
			pw.close();
			BufferedReader br=new BufferedReader(
					new InputStreamReader(pro.getErrorStream())
					);
			String line=null;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		PError();
		POutput();
	}
}
class PTest{
	public static void main(String[] args){
		try{
			Scanner sc=new Scanner(System.in);
			PrintStream ps=new PrintStream(
					new FileOutputStream("file/io/Process.txt")
					);
			sc.useDelimiter("\n");
			while(sc.hasNext()){
				ps.println("new line:  "+sc.next());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}






