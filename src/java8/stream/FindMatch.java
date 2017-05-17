package java8.stream;

import java.util.List;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ����ƥ��
 *
 */
public class FindMatch {
	List<Dish> list = DishManager.list;
	@Test
	public void TestAnyMatch(){
		//anyMatch������ƥ��һ��Ԫ��
		if (list.stream().anyMatch(e->e.getCalories()>700))
			System.out.println("matched !");
	}
	@Test
	public void TestAllMatch(){
		//allMatch������Ԫ�ض�ƥ��
		if(list.stream().allMatch(e->e.isVegetarian())){
			System.out.println("all is vegetarian!");
		}else {
			System.out.println("not all is vegetarian!");
		}
	}
	@Test
	public void TestNoneMatch(){
		//noneMatch��û��ƥ���Ԫ��
		if(list.stream().noneMatch(e->e.getType().equals(Dish.Type.FISH))){
			System.out.println("has fish");
		}else {
			System.out.println("donnot has fish");
		}
	}
	@Test
	public void TestFindAny(){
		//�ҵ�����һ�������أ�
		list.stream()
		.filter(e->e.getType().equals(Dish.Type.MEAT))
		.findAny()
		.ifPresent(System.out::println);
	}
	@Test
	public void  TestFindFirst(){
		//�ҵ���һ�������أ�
		list.stream()
		.filter(e->e.getType().equals(Dish.Type.MEAT))
		.findFirst()
		.ifPresent(System.out::println);
	}
}













