package java8.lambda;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 *
 *lambda�򵥽���
 */
public class LambdaTest {
	//ֻ�к���ʽ�ӿڲ�����Ϊlambda����
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
