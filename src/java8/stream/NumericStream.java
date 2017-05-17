package java8.stream;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
/**
 * 
 * @author dtdyq
 * 
 * ��ֵ��������Ƶ��װ���������ģ��ṩ����ֵ��ר�ô���
 *
 */
public class NumericStream {
	List<Dish> menu = DishManager.list;
	@SuppressWarnings("unused")
	@Test
	public void TestNumeric(){
		int sum = menu.stream()
				.mapToInt(Dish::getCalories)
				.sum();
		System.out.println(sum);
		//ת�����������
		IntStream stream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> baxStream = stream.boxed();
		//optional
		OptionalInt max = menu.stream()
				.mapToInt(Dish::getCalories)
				.max();
		System.out.println(max.orElse(-1));
		//����һ����Χ������
		IntStream is = IntStream.range(0, 100)
				.filter(e->e%2==0);
		System.out.println(is.count());
	}
	@Test
	public void Example(){
		//�ҳ�һ����Χ�ڵķ��Ϲ��ɶ������������
		//ע��flatmap������ߵĲ���ӳ�䵽�ұ߿��ܳ��ֲ�ֹһ�����ʱ����Ӧ��ʹ��flatmap
		Stream<int[]> ggn = IntStream.rangeClosed(1, 100)
				.boxed().flatMap(a->
					IntStream.rangeClosed(a, 100)
					.filter(b->Math.sqrt(a*a+b*b)%1==0)
					.mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
						);
		ggn.limit(5).forEach(i->System.out.println(i[0]+" "+i[1]+" "+i[2]));
	}
}












