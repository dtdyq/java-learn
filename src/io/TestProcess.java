package io;
import java.io.*;
import java.util.Scanner;
/**
 * 
 * @author dtdyq
 *	Runtime�����exec()�����������б����������ĳ���
 *	����һ��Process������java�����������ӽ���
 *	����һ�����ַ�������������java������ӽ���֮��ͨ��;
 *		getErrorStream()   ��ȡ�ӽ��̵Ĵ�����
 *		getOutputStream()  ��ȡ�ӽ��̵������
 *		getInputStream     ��ȡ�ӽ��̵�������
 */
public class TestProcess {
	//�ӽ��������������
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
			//��ȡpro�������:
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






