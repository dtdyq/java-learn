package java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import java8.argsFunction.Apple;
import static java8.lambda.Summary.*;

/**
 * 
 * @author dtdyq
 * 
 * ʹ��lambda����ʵ�ָ����ӹ���
 *
 */
public class Composite {
	List<Apple> list = Arrays.asList(
			new Apple("green",34.34),
			new Apple("red",76.33),
			new Apple("blue",12.87),
			new Apple("green",74.65),
			new Apple("red",34.34)
			);
	@org.junit.Test
	public void Test(){
		//�Ƚ������ϣ�
		//����
		list.sort(Comparator.comparing(Apple::getWeight).reversed());
		print(list);
		//�Ƚ���������ʹ�������Ƚϣ���ʹ����ɫ�Ƚϣ�
		list.sort(Comparator.comparing(Apple::getWeight)
				.thenComparing(Apple::getColor)
				);
		print(list);
		
		//ν�ʸ��ϣ�negate��and��or
		//�������ϣ�
		Function<Integer,Integer> f = x->x+1;
		Function<Integer,Integer> g = x->x*2;
		Function<Integer,Integer> h = f.andThen(g);
		int res = h.apply(10);
		System.out.println(res);
		Function<Integer,Integer> k = f.compose(g);
		int res2 = k.apply(3);
		System.out.println(res2);
		
	}
}










