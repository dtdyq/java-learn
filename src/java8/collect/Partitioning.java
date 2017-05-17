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
 * ����������������������һ��ν��(����һ��boolean�ĺ���)��Ϊ���ຯ������Ϊ��������
 * ���Է���Ϊtrue��false��������
 *
 */
public class Partitioning {
	List<Dish> menu = DishManager.list;
	@Test
	public void testPartition(){
		//ͨ���Ƿ�����ʳ�Բ��Ƚ�������,ֻ��ȡ���ȵ����֣�
		Map<Boolean, List<String>> partVerge = menu.stream()
				.collect(Collectors.partitioningBy(e->e.isVegetarian(),
						Collectors.mapping(e->e.getName(), Collectors.toList())
						));
		System.out.println(partVerge);
		//�ڷ�����ʹ��groupBy����ʳ�ͷ���ʳ�������ͽ��з��飺
		Map<Boolean,Map<Dish.Type,List<Dish>>> partGroup = menu.stream()
				.collect(Collectors.partitioningBy(e->e.isVegetarian(),
						Collectors.groupingBy(Dish::getType,
								Collectors.toList()
								)
						));
		System.out.println(partGroup);
		//��ȡ��ʳ�ͷ���ʳ��������͵Ĳˣ�
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
		//���ӣ������ְ����Ƿ����������з��ࣺ
		List<Integer> list = getPrime(1000).get(true);
		list.forEach(System.out::println);
	}
	//�����жϸ�������n�Ƿ���������
	public static boolean isPrime(int num){
		int snum = (int)Math.sqrt(num);
		return IntStream.rangeClosed(2, snum).noneMatch(n->num%n==0);
	}
	//���ظ�����Χ�ڵ�����������
	public static Map<Boolean,List<Integer>> getPrime(int n){
		return IntStream.rangeClosed(2, n)
				.boxed()
				.collect(Collectors.partitioningBy(e->isPrime(e)));
	}
}














