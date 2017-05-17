package java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import java8.argsFunction.Apple;

/**
 * 
 * @author dtdyq
 * ��������
 *
 */
public class MethodReference {
	public static void main(String[] args){
		List<Apple> list = Arrays.asList(
				new Apple("green",34.34),
				new Apple("red",76.33),
				new Apple("blue",12.87)
				);
		//ʹ�÷�������
		list.sort(Comparator.comparingDouble(Apple::getWeight));
		
		list.forEach(System.out::println);
		
		//���캯�����ã�
		Supplier<Apple> sp = Apple::new;
		Apple ap = sp.get();
		System.out.println(ap.getColor());
		
		Function<Double,Apple> fp = Apple::new;
		Apple ap2 = fp.apply(23.76);
		System.out.println(ap2.getWeight());
		/*
		 * �ȼ��ڣ�
		 * Function<double,Stirng> fp=(double weight)->new Apple(weight);
		 * Apple ap2 = fp.apply(23.76);
		 */
		
		//eg:
		BiFunction<String,Double,Apple> bp = Apple::new;
		Apple ap3 = bp.apply("green", 84.23);
		System.out.println(ap3);
		
 	}
}















