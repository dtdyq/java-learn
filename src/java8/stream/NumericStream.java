package java8.stream;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
/**
 * 
 * @author dtdyq
 * 
 * 数值流：避免频繁装箱拆箱的消耗，提供对数值的专用处理
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
		//转换会对象流：
		IntStream stream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> baxStream = stream.boxed();
		//optional
		OptionalInt max = menu.stream()
				.mapToInt(Dish::getCalories)
				.max();
		System.out.println(max.orElse(-1));
		//生成一定范围的流：
		IntStream is = IntStream.range(0, 100)
				.filter(e->e%2==0);
		System.out.println(is.count());
	}
	@Test
	public void Example(){
		//找出一定范围内的符合勾股定理的三个数：
		//注意flatmap，当左边的参数映射到右边可能出现不止一个结果时，就应该使用flatmap
		Stream<int[]> ggn = IntStream.rangeClosed(1, 100)
				.boxed().flatMap(a->
					IntStream.rangeClosed(a, 100)
					.filter(b->Math.sqrt(a*a+b*b)%1==0)
					.mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
						);
		ggn.limit(5).forEach(i->System.out.println(i[0]+" "+i[1]+" "+i[2]));
	}
}












