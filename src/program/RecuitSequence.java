package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 2017/3/24.
 * 如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列。例如：
 {1, 2, 1}, {15, 78, 78, 15} , {112} 是回文序列,
 {1, 2, 2}, {15, 78, 87, 51} ,{112, 2, 11} 不是回文序列。
 现在给出一个数字序列，允许使用一种转换操作：
 选择任意两个相邻的数，然后从序列移除这两个数，并用这两个数字的和插入到这两个数
 之前的位置(只插入一个和)。
 现在对于所给序列要求出最少需要多少次操作可以将其变成回文序列。

 输入描述:
 输入为两行，第一行为序列长度n ( 1 ≤ n ≤ 50)
 第二行为序列中的n个整数item[i]  (1 ≤ iteam[i] ≤ 1000)，以空格分隔。

 输出描述:
 输出一个数，表示最少需要的转换次数

 输入例子:
 4
 1 1 1 3
 输出例子:
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











