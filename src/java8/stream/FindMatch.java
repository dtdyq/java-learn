package java8.stream;

import java.util.List;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 查找匹配
 *
 */
public class FindMatch {
	List<Dish> list = DishManager.list;
	@Test
	public void TestAnyMatch(){
		//anyMatch：至少匹配一个元素
		if (list.stream().anyMatch(e->e.getCalories()>700))
			System.out.println("matched !");
	}
	@Test
	public void TestAllMatch(){
		//allMatch：所有元素都匹配
		if(list.stream().allMatch(e->e.isVegetarian())){
			System.out.println("all is vegetarian!");
		}else {
			System.out.println("not all is vegetarian!");
		}
	}
	@Test
	public void TestNoneMatch(){
		//noneMatch：没有匹配的元素
		if(list.stream().noneMatch(e->e.getType().equals(Dish.Type.FISH))){
			System.out.println("has fish");
		}else {
			System.out.println("donnot has fish");
		}
	}
	@Test
	public void TestFindAny(){
		//找到任意一个即返回：
		list.stream()
		.filter(e->e.getType().equals(Dish.Type.MEAT))
		.findAny()
		.ifPresent(System.out::println);
	}
	@Test
	public void  TestFindFirst(){
		//找到第一个即返回：
		list.stream()
		.filter(e->e.getType().equals(Dish.Type.MEAT))
		.findFirst()
		.ifPresent(System.out::println);
	}
}













