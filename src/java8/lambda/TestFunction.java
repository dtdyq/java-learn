package java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import java8.argsFunction.Apple;

/**
 * 
 * @author dtdyq
 *
 */
public class TestFunction {
	public static <T,R> List<R> map(List<T> list,Function<T,R> f){
		List<R> res = new ArrayList<>();
		
		for(T t:list){
			res.add(f.apply(t));
		}
		return res;
	}
	@org.junit.Test
	public void Test(){
		List<Apple> list = Arrays.asList(
				new Apple("green",34.34),
				new Apple("red",76.33),
				new Apple("blue",12.87)
				);
		List<String> res = map(list,e->e.getColor());
		
		res.forEach(e->System.out.println(e));
	}
}
