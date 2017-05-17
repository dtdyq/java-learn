package java8.stream;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;
import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 筛选和切片
 *
 */
public class SelectSlice {
	@Test
	public void TestFilter(){
		//filter:筛选：
		List<Dish> menu = DishManager.list;
		long num = menu.stream().filter(p->p.isVegetarian()).count();
		System.out.println(num);
	}
	@Test
	public void TestDistinct(){
		//distinct:消除重复元素：
		List<Integer> list = Arrays.asList(2,3,4,5,6,8,9,6,43,4,7,65,8,3)
				.stream()
				.filter(d->d%2==0)
				.distinct()
				.collect(toList());
		list.forEach(System.out::println);
	}
	@Test
	public void TestLimit(){
		//截断流：
		List<Integer> list = Arrays.asList(76,454,2,64,7624,13,988)
				.stream()
				.sorted()
				.limit(4)
				.collect(toList());
		list.forEach(System.out::println);
	}
	@Test
	public void TestSkip(){
		//skip:跳过元素：
		List<String> list = Arrays.asList("weewef","iqbwxw","iqwbxf","bdw","qibx")
				.stream()
				.filter(p->p.length()>4)
				.skip(2)
				.collect(toList());
		list.forEach(System.out::println);
		
	}
}













