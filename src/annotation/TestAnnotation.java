package annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * Annotation��һ��ע�Ͳ�ͬ���Ǵ����е�һ��������
 * �����ڱ��롢����ء�����ʱ����ȡ��Annotation�൱
 * ��һ�����η������������ΰ����ࡢ����������������Ա�����������;ֲ�����
 * 
 * ���ʺʹ���Annotation�Ĺ��߱���ΪAnnotation Porcessing Tool(APT)
 * 
 * ���������Annotation��
 * 		@ Override	��д���෽��
 * 		@ deprecated		��ʶ�ѹ�ʱ
 * 		@ SuppressWarning	���Ʊ���������
 * 		@ SafeVarargs	java7�ġ�����Ⱦ������
 * 		@ FunctionalInterface    java8����ָ��ĳ���ӿڱ����Ǻ���ʽ�ӿ�(
 * 									ֻ��Ĭ�Ϸ�����static������һ�����󷽷�)
 * @author dtdyq
 *
 */
public class TestAnnotation {
	public static void main(String[] args) {
		faultMethod(new List[3]);
	}
	@SafeVarargs
	public static void faultMethod(List<String>...listArray){
		List[] list=listArray;
		List<Integer> myList=new ArrayList<>();
		myList.add(new Random().nextInt(100));
		list[0]=myList;
		System.out.println(list[0].get(0));
	}
}

@FunctionalInterface//ֻ�����ڽӿ�
interface FuncInterface{
	static void foo(){
		System.out.println("foo moethd");
	}
	default void bar(){
		System.out.println("bar method");
	}
	void test();
}



