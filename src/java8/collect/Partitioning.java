package java8.collect;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import java8.stream.Dish;
import java8.stream.DishManager;

/**
 * 
 * @author dtdyq
 * 
 * 分区：分组的特殊情况，由一个谓词(返回一个boolean的函数)作为分类函数，称为分区函数
 * 可以分区为true和false两个部分
 *
 */
public class Partitioning {
	List<Dish> menu = DishManager.list;
	@Test
	public void testPartition(){
		//通过是否是素食对菜肴进行区分,只获取菜肴的名字：
		Map<Boolean, List<String>> partVerge = menu.stream()
				.collect(Collectors.partitioningBy(e->e.isVegetarian(),
						Collectors.mapping(e->e.getName(), Collectors.toList())
						));
		System.out.println(partVerge);
		//在分区中使用groupBy对素食和非素食按照类型进行分组：
		Map<Boolean,Map<Dish.Type,List<Dish>>> partGroup = menu.stream()
				.collect(Collectors.partitioningBy(e->e.isVegetarian(),
						Collectors.groupingBy(Dish::getType,
								Collectors.toList()
								)
						));
		System.out.println(partGroup);
		//获取素食和非素食中热量最低的菜：
		Map<Boolean,String> partMin = menu.stream()
			.collect(Collectors.partitioningBy(e->e.isVegetarian(),
					Collectors.collectingAndThen(
							Collectors.minBy(Comparator.comparingDouble(Dish::getCalories))
							,e->e.get().getName())
					));
		System.out.println(partMin);
	}
	@Test
	public void testExample(){
		//例子：将数字按照是否是质数进行分类：
		List<Integer> list = getPrime(1000).get(true);
		list.forEach(System.out::println);
	}
	//用于判断给定的数n是否是质数：
	public static boolean isPrime(int num){
		int snum = (int)Math.sqrt(num);
		return IntStream.rangeClosed(2, snum).noneMatch(n->num%n==0);
	}
	//返回给定范围内的所有质数：
	public static Map<Boolean,List<Integer>> getPrime(int n){
		return IntStream.rangeClosed(2, n)
				.boxed()
				.collect(Collectors.partitioningBy(e->isPrime(e)));
	}
}














