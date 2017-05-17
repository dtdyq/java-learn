package java8.functional;

import java.util.Arrays;

/**
 * Created by dtdyq on 2017/4/2.
 * 并行化数组操作
 */
public class TestArrays {
    public static void main(String[] args){
        double[] d=new double[100];
        //初始化数组：
        Arrays.parallelSetAll(d,i->100.0-i);
        Arrays.parallelPrefix(d,(x,y)->x+y);
        Arrays.parallelSort(d);
        for(int i=0;i<10;i++){
            System.out.println(d[i]+" ");
        }
    }
}
