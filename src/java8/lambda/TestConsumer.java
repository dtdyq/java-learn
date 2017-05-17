package java8.lambda;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import java8.argsFunction.*;
/**
 * 
 * @author dtdyq
 *
 */
public class TestConsumer {
	public static void printApple(List<Apple> apples,Consumer<Apple> c){
		for(Apple apple:apples){
			c.accept(apple);
		}
	}
	@org.junit.Test
	public void Test(){
		List<Apple> list = Arrays.asList(
				new Apple("green",34.34),
				new Apple("red",76.33),
				new Apple("blue",12.87)
				);
		
		printApple(list,e->System.out.println(e.getColor()));
	}
}
