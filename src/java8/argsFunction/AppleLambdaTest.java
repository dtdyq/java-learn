package java8.argsFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
/**
 * 
 * @author dtdyq
 *
 */
public class AppleLambdaTest {
	public static <T> List<T> filter(List<T> list,Predicate<T> p){
		List<T> res = new ArrayList<>();
		for(T t:list){
			if(p.test(t)){
				res.add(t);
			}
		}
		return res;
	}
	@Test
	public void TestAppleLambda(){
		List<Apple> list = Arrays.asList(
				new Apple("green",34.34),
				new Apple("red",76.33),
				new Apple("blue",12.87)
				);
		//使用lambda形式进行任意的筛选：
		List<Apple> res = filter(list,e->e.getColor().equals("green"));
		//使用内置的sort函数进行排序：
		res.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));
		
	}
}












