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
 * 使用lambda复合实现更复杂功能
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
		//比较器复合：
		//逆序：
		list.sort(Comparator.comparing(Apple::getWeight).reversed());
		print(list);
		//比较器链：先使用重量比较，再使用颜色比较：
		list.sort(Comparator.comparing(Apple::getWeight)
				.thenComparing(Apple::getColor)
				);
		print(list);
		
		//谓词复合：negate、and、or
		//函数复合：
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










