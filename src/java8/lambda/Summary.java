package java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import java8.argsFunction.Apple;

/**
 * 
 * @author dtdyq
 *
 * ͨ����ƻ�����������ܽ�lambda��method reference
 */
public class Summary {
	List<Apple> list = Arrays.asList(
			new Apple("green",34.34),
			new Apple("red",76.33),
			new Apple("blue",12.87)
			);
	//��һ������ʵ��Comparator�ӿڣ�
	class AppleComparator implements Comparator<Apple>{
		@Override
		public int compare(Apple o1, Apple o2) {
			return o1.getWeight()>o2.getWeight()?1:
				o1.getWeight()==o2.getWeight()?0:-1;
		}
		@Test
		public void TestCmp1(){
			list.sort(new AppleComparator());
		}
		
	}
	//�ڶ��ַ�����ʹ�������ڲ��ࣺ
	@Test
	public void TestCmp2(){
		list.sort(new Comparator<Apple>(){

			@Override
			public int compare(Apple a, Apple b) {
				return a.getColor().compareTo(b.getColor());
			}
		});
		print(list);
	}
	//ʹ��lambda���ʽ��
	@Test
	public void TestCmp3(){
		list.sort((Apple a,Apple b)->
			a.getColor().compareTo(b.getColor()));
		print(list);
	}
	//ʹ�÷������ã�
	@Test
	public void TestCmp4(){
		list.sort(Comparator.comparing(Apple::getColor));
		print(list);
	}
	public static void print(List<Apple> list){
		list.forEach(System.out::println);
	}
}















