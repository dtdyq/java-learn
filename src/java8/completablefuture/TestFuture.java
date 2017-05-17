package java8.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 使用传统的Future实现异步计算：
 *
 */
public class TestFuture {
	static int doSomething(){
		return IntStream.rangeClosed(0, 100)
				.reduce(0, (a,b)->{
					return a+b;
				});
	}
	static void doSomethingElse(){
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	@Test
	public void test(){
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Future<Integer> res = exec.submit(new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				return doSomething();
			}
		});
		doSomethingElse();
		try {
			System.out.println(res.get(1, TimeUnit.SECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}














