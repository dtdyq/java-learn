package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	实现LineNumberReader类的功能（LineNumberInputStream类相同）
 */
public class DemoLineNumber {
	//行号：
	private int lineNumber;
	//输入缓冲流
	private BufferedReader br;
	//存储一行数组的缓冲区：
	private char[] buffLine;
	//缓冲区字符数组已读取位置：
	private int pointer;
	public DemoLineNumber(Reader read){
		br=new BufferedReader(read);
		lineNumber=0;
		try {
			String str=br.readLine();
			if(str!=null){
				buffLine=(str+"\n").toCharArray();
			}else{
				buffLine=null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pointer=0;
	}
	//设置行号：
	public void setLineNumber(int number){
		this.lineNumber=number;
	}
	//获取行号：
	public int getLineNumber(){
		return lineNumber;
	}
	//读取一个字符：
	public int read() throws IOException{
		if(buffLine!=null){
			if(pointer<buffLine.length){
				return buffLine[pointer++];
			}
			else{
				String str=br.readLine();
				if(str!=null){
					buffLine=(str+"\n").toCharArray();
					pointer=0;
					lineNumber++;
					return buffLine[pointer++];
				}
				else{
					buffLine=null;
					return -1;
				}
			}
		}
		else{
			return -1;
		}
	}
	//读取一行:
	public String readLine() throws IOException{
		lineNumber++;
		StringBuffer buff=new StringBuffer();
		int ch=0;
		while((ch=br.read())!=-1){
			if(ch=='\r')
				continue;
				if(ch=='\n'){
					return buff.toString();
				}
			    else{
				buff.append((char)ch);
			}
		}
		if(buff.length()!=0){
			
			return buff.toString();
		}
		return null;
	}
	//读取固定数量的字符数组：
	public int read(char[] buff) throws IOException{
		if(buffLine!=null){
			int len=buffLine.length;
			int i=0;
			for(;pointer<len&&i<buff.length;pointer++,i++){
				buff[i]=buffLine[pointer];
			}
			if(i==buff.length){
				return i;
			}
			while(true){
				String str=br.readLine();
				if(str!=null){
					buffLine=(str+"\n").toCharArray();
					pointer=0;
					lineNumber++;
					for(;pointer<buffLine.length&&i<buff.length;pointer++,i++){
						buff[i]=buffLine[pointer];
					}
					if(i==buff.length){
						return i;
					}
				}
				else{
					buffLine=null;
					return i;
				}
			}
		}
		else{
			return -1;
		}
	}
	public void close() throws IOException{
		br.close();
	}
	public static void main(String[] args) throws Exception{
		//测试：
		DemoLineNumber dln=new DemoLineNumber(
				new FileReader("file/io/FileTest.txt"));
		/*
		int i=-1;
		while((i=dln.read())!=-1){
			System.out.print(dln.getLineNumber()+"-->"+new String(Character.toChars(i)));
		}
		*/
		
		String line=null;
		while((line=dln.readLine())!=null){
			System.out.println(dln.getLineNumber()+"-->"+line);
		}
		/*
		int len=-1;
		char[] ch=new char[8];
		while((len=dln.read(ch))!=-1){
			System.out.println(dln.getLineNumber()+"--->"+new String(ch,0,len));
		}
		*/
	}
}





