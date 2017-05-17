package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * @author dtdyq
 * 
 * 将sql中的数据提取为TXT
 *
 */
public class SqlProcess {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream("f:/weibodatabase.sql"),"utf-8"
				)
				);
		BufferedWriter bw = null;
		String line = "";
		
		while((line = br.readLine())!=null){
			if(line.startsWith("CREATE TABLE ")){
				String tmp = line.substring(line.indexOf("`")+1, line.lastIndexOf("`"));
				bw = new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream("f:/"+tmp+".txt"),"utf-8"
						));
				process(br,bw,tmp);
			}
		}
	}
	static void process(BufferedReader br,BufferedWriter bw,String filename) throws IOException{
		String line = "";
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine()) != null && line.startsWith("  `")){
			
			String tmp = line.substring(line.indexOf("`")+1, line.lastIndexOf("`"));
			sb.append(tmp+"\t");
		}
		bw.write(sb.toString()+"\n");
		while((line = br.readLine())!=null){
			if(line.startsWith("INSERT INTO `"+filename+"` VALUES (")){
				bw.write(getLine(line)+"\n");
			}
		}
		bw.close();
	}
	static String getLine(String line){
		StringBuilder res = new StringBuilder();
		String value = line.substring(line.indexOf("(")+1, line.lastIndexOf(")"));
		for(String tmp:value.split(", ")){
			res.append(tmp+"\t");
		}
		return res.toString();
	}
}


















