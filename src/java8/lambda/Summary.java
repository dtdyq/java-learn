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
 * 通过对苹果的排序来总结lambda和method reference
 */
public class Summary {
	List<Apple> list = Arrays.asList(
			new Apple("green",34.34),
			new Apple("red",76.33),
			new Apple("blue",12.87)
			);
	//第一方案：实现Comparator接口：
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
	//第二种方案：使用匿名内部类：
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
	//使用lambda表达式：
	@Test
	public void TestCmp3(){
		list.sort((Apple a,Apple b)->
			a.getColor().compareTo(b.getColor()));
		print(list);
	}
	//使用方法引用：
	@Test
	public void TestCmp4(){
		list.sort(Comparator.comparing(Apple::getColor));
		print(list);
	}
	public static void print(List<Apple> list){
		list.forEach(System.out::println);
	}
}















