package program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Admin on 2017/3/24.
 ��һ��Բ��������ԭ���Բ��֪��Բ�İ뾶��ƽ����������Բ�ϵĵ���Һ������궼�������ĵ�
 �����ŵģ�������Ѱ��һ���㷨��������ŵĵ�ĸ���
 ���磺�뾶��ƽ�����Ϊ25
 ���ŵĵ���У�(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)��һ��12���㡣
 ��������:
 ����Ϊһ����������ΪԲ�뾶��ƽ��,��Χ��32λint��Χ�ڡ�


 �������:
 ���Ϊһ����������Ϊ���ŵĵ�ĸ���
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












