package java8.refactor;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ʹ����־���е���
 *
 */
public class Debug {
	@Test
	public void test(){
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		list.stream()
		.map(e->e+17)
		.filter(e->e%2==0)
		.limit(3)
		.forEach(System.out::println);
		//ʹ��peek���е��ԣ�
		list.stream()
		.map(e->e+17)
		.peek(e->System.out.println("after map:"+e))
		.filter(e->e%2==0)
		.peek(e->System.out.println("after filter:"+e))
		.limit(3)
		.peek(e->System.out.println("after limit:"+e))
		.forEach(System.out::println);
	}

}









