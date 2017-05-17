package java8.collect;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import java8.stream.Dish;
import java8.stream.DishManager;

/**
 * 
 * @author dtdyq
 * 
 * 利用collector进行归约和汇总
 *
 */
public class GatherAndReduce {
	List<Dish> menu = DishManager.list;
	@Test
	public void testCounting(){
		//counting:计数
		long count = menu.stream()
				.collect(Collectors.counting());
		//等价于：
		long counter = menu.stream().count();
		System.out.println(count);
		System.out.println(counter);
	}
	@Test
	public void testMaxMin(){
		//求最大值和最小值：
		Comparator<Dish> dishCmp = Comparator.comparing(Dish::getCalories);
		Optional<Dish> maxCal = menu.stream()
				.collect(Collectors.maxBy(dishCmp));
		System.out.println(maxCal);
		
		Optional<Dish> minCal = menu.stream()
				.collect(Collectors.minBy(Comparator.comparing(e->e.getCalories())));
		System.out.println(minCal);
	}
	@Test
	public void testStatics(){
		//求最大值最小值平均数等统计方法
		DoubleSummaryStatistics total = menu.stream()
				.collect(Collectors.summarizingDouble(e->e.getCalories()));
		System.out.println(total);
	}
	@Test
	public void testJoining(){
		//连接字符串，内部使用StringBuilder进行join：
		//将所有菜肴的名字汇总：
		String name = menu.stream()
				.map(e->e.getName())
				.collect(Collectors.joining());
		System.out.println(name);
		//上述方法返回的结果可读性较差，可以使用重载方法：在各个字符串之间使用指定分隔符
		String name2 = menu.stream()
				.map(Dish::getName)
				.collect(Collectors.joining(", "));
		System.out.println(name2);
		//另一个重载方法：在字符串开始和结束也指定字符串
		String name3 = menu.stream()
				.map(Dish::getName)
				.collect(Collectors.joining(", ", "[", "]"));
		System.out.println(name3);
	}
	@Test
	public void retestReduce(){
		//用reduce进行更丰富和广义的归约：
		//总的卡路里：
		double total = menu.stream()
				.map(e->e.getCalories())
				.reduce(0,(a,b)->a+b);
		System.out.println(total);
		//另一种表示：初始值、转换函数和累积函数：
		double total2 = menu.stream()
				.collect(Collectors.reducing(0,Dish::getCalories,Integer::sum));
		System.out.println(total2);
		//第三种表示方法：
		double total3 = menu.stream()
				.map(e->e.getCalories())
				.reduce(Integer::sum).get();
		System.out.println(total3);
		//第四中表示形式：
		double total4 = menu.stream()
				.mapToDouble(e->e.getCalories())
				.sum();
		System.out.println(total);
		System.out.println(total4);
		
		//卡路里最大的食物：
		long max = menu.stream()
				.map(e->e.getCalories())
				.reduce(0, (a,b)->a>b?a:b);
		System.out.println(max);
	}
}


















