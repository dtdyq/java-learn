package java8.lambda;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 *
 *lambda简单介绍
 */
public class LambdaTest {
	//只有函数式接口才能作为lambda传参
	public static void process(Runnable r){
		new Thread(r).start();
	}
	@Test
	public void example(){
		Runnable r1=()->
			System.out.println("only functional interface!");
			
		process(r1);
		process(()->System.out.println("functional interface 2"));
	}

}
