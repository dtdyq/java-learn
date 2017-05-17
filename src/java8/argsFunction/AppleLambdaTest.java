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
		//ʹ��lambda��ʽ���������ɸѡ��
		List<Apple> res = filter(list,e->e.getColor().equals("green"));
		//ʹ�����õ�sort������������
		res.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));
		
	}
}












