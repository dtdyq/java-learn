package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 归约
 *
 */
public class Reducing {
	@Test
	public void TestReduce(){
		//求和：
		List<Integer> list = Arrays.asList(2,7,9,4,7,1);
		int sum = list.stream()
				.reduce(0, (a,b)->a+b);
		sum = list.stream().reduce(0, Integer::sum);
		System.out.println(sum);
		//求乘积：
		int product = list.stream()
				.reduce(1, (a,b)->a*b);
		System.out.println(product);
	}
	@Test
	public void TestMaxMin(){
		//求最大值：
		List<Integer> list = Arrays.asList(2,7,9,4,7,1);
		Optional<Integer> max = list.stream()
				.reduce((a,b)->a>b?a:b);
		System.out.println(max.get());		
		//求最小值
		Optional<Integer> min = list.stream()
				.reduce(Integer::min);
		System.out.println(min.get());
	}
}





















