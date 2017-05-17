package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ��Լ
 *
 */
public class Reducing {
	@Test
	public void TestReduce(){
		//��ͣ�
		List<Integer> list = Arrays.asList(2,7,9,4,7,1);
		int sum = list.stream()
				.reduce(0, (a,b)->a+b);
		sum = list.stream().reduce(0, Integer::sum);
		System.out.println(sum);
		//��˻���
		int product = list.stream()
				.reduce(1, (a,b)->a*b);
		System.out.println(product);
	}
	@Test
	public void TestMaxMin(){
		//�����ֵ��
		List<Integer> list = Arrays.asList(2,7,9,4,7,1);
		Optional<Integer> max = list.stream()
				.reduce((a,b)->a>b?a:b);
		System.out.println(max.get());		
		//����Сֵ
		Optional<Integer> min = list.stream()
				.reduce(Integer::min);
		System.out.println(min.get());
	}
}





















