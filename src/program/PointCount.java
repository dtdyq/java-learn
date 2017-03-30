package program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Admin on 2017/3/24.
 有一个圆心在坐标原点的圆，知道圆的半径的平方。假设在圆上的点而且横纵坐标都是整数的点
 是优雅的，现在想寻找一个算法计算出优雅的点的个数
 例如：半径的平方如果为25
 优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
 输入描述:
 输入为一个整数，即为圆半径的平方,范围在32位int范围内。


 输出描述:
 输出为一个整数，即为优雅的点的个数
 */
public class PointCount {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(
                System.in
        ));
        int num=Integer.parseInt(br.readLine());
        int count=0;
        int temp=(int)Math.sqrt(num);
        for(int i=0;i<=temp;i++){
            for(int j=0;j<=temp;j++){
                if(i*i+j*j==num){
                    if(i==0||j==0){
                        count+=2;
                    }
                    else{
                        count+=4;
                    }
                }
            }
        }
        System.out.println(count);
    }
}












