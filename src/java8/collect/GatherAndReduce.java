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
 * ����collector���й�Լ�ͻ���
 *
 */
public class GatherAndReduce {
	List<Dish> menu = DishManager.list;
	@Test
	public void testCounting(){
		//counting:����
		long count = menu.stream()
				.collect(Collectors.counting());
		//�ȼ��ڣ�
		long counter = menu.stream().count();
		System.out.println(count);
		System.out.println(counter);
	}
	@Test
	public void testMaxMin(){
		//�����ֵ����Сֵ��
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
		//�����ֵ��Сֵƽ������ͳ�Ʒ���
		DoubleSummaryStatistics total = menu.stream()
				.collect(Collectors.summarizingDouble(e->e.getCalories()));
		System.out.println(total);
	}
	@Test
	public void testJoining(){
		//�����ַ������ڲ�ʹ��StringBuilder����join��
		//�����в��ȵ����ֻ��ܣ�
		String name = menu.stream()
				.map(e->e.getName())
				.collect(Collectors.joining());
		System.out.println(name);
		//�����������صĽ���ɶ��Խϲ����ʹ�����ط������ڸ����ַ���֮��ʹ��ָ���ָ���
		String name2 = menu.stream()
				.map(Dish::getName)
				.collect(Collectors.joining(", "));
		System.out.println(name2);
		//��һ�����ط��������ַ�����ʼ�ͽ���Ҳָ���ַ���
		String name3 = menu.stream()
				.map(Dish::getName)
				.collect(Collectors.joining(", ", "[", "]"));
		System.out.println(name3);
	}
	@Test
	public void retestReduce(){
		//��reduce���и��ḻ�͹���Ĺ�Լ��
		//�ܵĿ�·�
		double total = menu.stream()
				.map(e->e.getCalories())
				.reduce(0,(a,b)->a+b);
		System.out.println(total);
		//��һ�ֱ�ʾ����ʼֵ��ת���������ۻ�������
		double total2 = menu.stream()
				.collect(Collectors.reducing(0,Dish::getCalories,Integer::sum));
		System.out.println(total2);
		//�����ֱ�ʾ������
		double total3 = menu.stream()
				.map(e->e.getCalories())
				.reduce(Integer::sum).get();
		System.out.println(total3);
		//�����б�ʾ��ʽ��
		double total4 = menu.stream()
				.mapToDouble(e->e.getCalories())
				.sum();
		System.out.println(total);
		System.out.println(total4);
		
		//��·������ʳ�
		long max = menu.stream()
				.map(e->e.getCalories())
				.reduce(0, (a,b)->a>b?a:b);
		System.out.println(max);
	}
}


















