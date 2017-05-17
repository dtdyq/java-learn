package java8.stream;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import static java.util.stream.Collectors.*;
/**
 * 
 * @author dtdyq
 * 
 * 流：数据源
 * 	      中间操作链
 *     终端操作
 *
 */
public class TestStream {
	List<Dish> menu = DishManager.list; 
	@Test
	public void StreamTest(){
		//获取三种热量最高的食物
		List<String> threeHighCaloricDishName = menu.stream()
				.sorted(Comparator.comparing(Dish::getCalories).reversed())
				.limit(3)
				.map(Dish::getName)
				.collect(toList());
		
		threeHighCaloricDishName.forEach(System.out::println);
	}
	@Test
	public void StreamAnaly(){
		List<String> list = menu.stream()
				.filter(d->{
					System.out.println("Filter "+d.getName());
					return d.getCalories()<400;
				})
				.map(m->{
					System.out.println("map "+m.getName());
					return m.getName();
				})
				.limit(2)
				.collect(toList());
		list.forEach(System.out::println);
	}
}












