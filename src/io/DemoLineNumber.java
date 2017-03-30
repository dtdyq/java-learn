package io;
import java.io.*;
/**
 * 
 * @author dtdyq
 *	ʵ��LineNumberReader��Ĺ��ܣ�LineNumberInputStream����ͬ��
 */
public class DemoLineNumber {
	//�кţ�
	private int lineNumber;
	//���뻺����
	private BufferedReader br;
	//�洢һ������Ļ�������
	private char[] buffLine;
	//�������ַ������Ѷ�ȡλ�ã�
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
	//�����кţ�
	public void setLineNumber(int number){
		this.lineNumber=number;
	}
	//��ȡ�кţ�
	public int getLineNumber(){
		return lineNumber;
	}
	//��ȡһ���ַ���
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
	//��ȡһ��:
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
	//��ȡ�̶��������ַ����飺
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
		//���ԣ�
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





