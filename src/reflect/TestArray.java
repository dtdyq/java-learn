package reflect;

import java.lang.reflect.Array;

import org.junit.Test;

/**
 * reflect��������һ��Array�࣬Array������Դ������е�����
 * ����Ϊ��ʾ������
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
		 //����һ������ʮ���������ά���飺
		 Object arr=Array.newInstance(String.class, 3,4,10);
		//��ȡ������Ķ�ά���飺
		Object arr2=Array.get(arr, 2);
		//�ڸö�ά����ĵڶ��еĵ�0��1��2���������
		Array.set(arr2, 2, new String[]{"java","C++","python"});
		
		//��õ����У���һ��һά���飺
		Object arr3=Array.get(arr2, 3);
		//�������еĵڰ����������Ϊ��lisp����
		Array.set(arr3, 8, "lisp"); 
		//ת��Ϊ��ͨ����ά����;
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
