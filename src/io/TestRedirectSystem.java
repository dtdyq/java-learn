package io;
import java.io.*;
import java.util.Scanner;
/**
 * 
 * @author dtdyq
 *	输出重定向：
 *		setErr(PrintStream err)--->重定向错误输出流
 *		setIn(InputStream in)  --->重定向标准输入流
 *		setOut(PrintStream out)--->重定向标准输出流
 */
public class TestRedirectSystem {
	public static void RErr() throws FileNotFoundException{
		PrintStream ps=new PrintStream("file/io/err.txt");
		System.setErr(ps);
		try{
			@SuppressWarnings("unused")
			int i=23/0;
		}catch(Exception e){
			e.printStackTrace();
		}
		ps.close();
	}
	public static void ROut() throws FileNotFoundException{
		PrintStream ps=new PrintStream("file/io/Out.txt");
		System.setOut(ps);
		Scanner sc=new Scanner(System.in);
		String line=null;
		while(!"over".equals(line=sc.nextLine())){
			System.out.println(line);
		}
		ps.close();
	}
	public static void RIn() throws IOException{
		InputStream is=new FileInputStream(new File("file/io/FileTest.txt"));
		System.setIn(is);
		Scanner sc=new Scanner(System.in);
		//将"\n"作为分隔符：
		sc.useDelimiter("\n");
		while(sc.hasNext()){
			System.out.print(sc.next());
		}
		is.close();
	}
	public static void main(String[] args) throws IOException{
		System.out.println("Redirect test:");
		RErr();
		ROut();
		RIn();
	}
	
}
