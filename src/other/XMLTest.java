package other;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by dtdyq on 2017/3/14.
 */
public class XMLTest {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(
                new FileReader("D:\\eclipse.xml")
        );
        String line=null;
        while((line=br.readLine())!=null)
            System.out.println(line);
    }
}
