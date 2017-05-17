package java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
/**
 * 
 * @author dtdyq
 *
 */
public class TestPredicate {
	public static <T> List<T> filter(List<T> list,Predicate<T> p){
		List<T> res = new ArrayList<>();
		list.forEach(e->{
			if(p.test(e)){
				res.add(e);
			}
		});
		return res;
	}
	@org.junit.Test
	public void Test(){
		List<String> list = Arrays.asList("ibceru","oenrewe","erety","owbrgey");
		List<String> res = filter(list,e->e.endsWith("y"));
		
		res.forEach(r->System.out.println(r));
		
	}

}
