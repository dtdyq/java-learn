package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 2017/3/24.
 * ���һ��������������֮���ԭ������һ���ľͳ���������������Ϊ�������С����磺
 {1, 2, 1}, {15, 78, 78, 15} , {112} �ǻ�������,
 {1, 2, 2}, {15, 78, 87, 51} ,{112, 2, 11} ���ǻ������С�
 ���ڸ���һ���������У�����ʹ��һ��ת��������
 ѡ�������������ڵ�����Ȼ��������Ƴ������������������������ֵĺͲ��뵽��������
 ֮ǰ��λ��(ֻ����һ����)��
 ���ڶ�����������Ҫ���������Ҫ���ٴβ������Խ����ɻ������С�

 ��������:
 ����Ϊ���У���һ��Ϊ���г���n ( 1 �� n �� 50)
 �ڶ���Ϊ�����е�n������item[i]  (1 �� iteam[i] �� 1000)���Կո�ָ���

 �������:
 ���һ��������ʾ������Ҫ��ת������

 ��������:
 4
 1 1 1 3
 �������:
 2
 */
public class RecuitSequence {
    public static boolean isRecur(List seq){
        boolean flag=true;
        int len=seq.size();
        for(int i=0;i<len/2;i++){
            if(seq.indexOf(i)!=seq.indexOf(len-i-1)){
                flag=false;
                break;
            }
        }
        return flag;
    }
    public static void main(String[] args){
//        Scanner sc=new Scanner(System.in);
//        int len=Integer.parseInt(sc.nextLine());
//        int seq[]=new int[len];
//        String[] str=sc.nextLine().split(" ");
//        for(int i=0;i<len;i++){
//            seq[i]=Integer.parseInt(str[i]);
//        }
        int count=0;
        int a[]={3,3,3,3};
        int len=4;
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(3);
        for(int i=0;i<list.size()-1;i++){
            list.add(i,list.remove(i)+list.remove(i+1));
            count++;
            if(isRecur(list)){
                break;
            }
        }
        System.out.println(count);


    }
}











