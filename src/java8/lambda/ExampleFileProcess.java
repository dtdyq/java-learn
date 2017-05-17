package java8.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExampleFileProcess {
	public static String processFile(FileProcessor precess){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("file/java8/lambda.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return precess.process(br);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@org.junit.Test
	public void Test() throws Exception{
		
		String oneLine = processFile(e->e.readLine());
		System.out.println(oneLine);
		
		String twoLine = processFile(e->e.readLine()+"\n"+e.readLine());
		System.out.println(twoLine);
	}
}
