package reflect;

import java.lang.reflect.Array;

import org.junit.Test;

/**
 * reflect包下面有一个Array类，Array对象可以代表所有的数组
 * 以下为演示方法：
 * @author dtdyq
 *
 */
public class TestArray {
	 public static void test1() {
		 Object arr=Array.newInstance(String.class, 10);
		 Array.set(arr, 3, "java");
		 Array.set(arr, 6, "C++");
		 Object o1=Array.get(arr, 3);
		 Object o2=Array.get(arr, 6);
		 System.out.println(o1);
		 System.out.println(o2);
		 
	}
	 public static void test2() {
		 //构造一个四行十列三层的三维数组：
		 Object arr=Array.newInstance(String.class, 3,4,10);
		//获取第三层的二维数组：
		Object arr2=Array.get(arr, 2);
		//在该二维数组的第二行的第0、1、2列添加内容
		Array.set(arr2, 2, new String[]{"java","C++","python"});
		
		//获得第三行，是一个一维数组：
		Object arr3=Array.get(arr2, 3);
		//将第三行的第八列内容添加为“lisp”：
		Array.set(arr3, 8, "lisp"); 
		//转换为普通的三维数组;
		String[][][] str=(String[][][])arr;
		System.out.println(str[2][3][8]);
		System.out.println(str[2][2][0]);
		System.out.println(str[2][2][1]);
		System.out.println(str[2][2][2]);
	 }
	public static void main(String[] args){
		test2();
		
	}
}
