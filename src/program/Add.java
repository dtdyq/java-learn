package program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Admin on 2017/3/24.
 *
 */
public class Add {
    static int reverse(int num){
        StringBuilder temp=new StringBuilder();
        temp.append(num+"");
        return Integer.parseInt(temp.reverse().toString());

    }
    static int reverseAdd(int a,int b){
        int a1=reverse(a);
        int b1=reverse(b);
        return a1+b1;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(
                new InputStreamReader(System.in)
        );

        String[] str=br.readLine().split(",");
        int result=reverseAdd(Integer.parseInt(str[0]),
                Integer.parseInt(str[1]));

        System.out.println(result);
    }
}
