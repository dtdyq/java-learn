package java8.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 使用CompletableFuture实现异步API案例
 * 
 * 创建一个最佳价格查询器，查询多个商店，依据给定的产品和服务找出最低的价格
 * 
 * 下一步见ShopNoBlock
 *
 */
public class ShopAsync{
	@Test
	public void test(){
		Shop shop = new Shop("myshop");
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Invoaction returned after "+invocationTime+" msecs");
		//现在可以同步执行其他任务
		//...............
		try {
			double price = futurePrice.get();
			System.out.printf("the price is %.2f%n", price);
		} catch(Exception e){
			throw new RuntimeException();
		}
		long retrieTime = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Price returned after "+retrieTime+" msecs");
	}
}
class Shop{
	protected String name;
	public Shop(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//同步的获取商品价格的方法
	public double getPrice(String product){
		return calculatePrice(product);
	}
	//异步的方法：
	public Future<Double> getPriceAsync(String product){
		CompletableFuture<Double> cf = new CompletableFuture<>();
		new Thread(()->{
			double d = calculatePrice(product);
			cf.complete(d);
		}).start();
		return cf;
	}
	//上述异步方法的改进：线程中可能出现异常，应该使用CompletableFuture将异常抛出
	public Future<Double> getPriceAsyncExcept(String product){
		CompletableFuture<Double> cf = new CompletableFuture<>();
		new Thread(()->{
			try {
			double d = calculatePrice(product);
			cf.complete(d);
			} catch(Exception e){
				cf.completeExceptionally(e);
			}
		}).start();
		return cf;
	}
	//使用CompletableFuture的工厂方法更方便地实现上述功能
	public Future<Double> getPriceAsyncFactory(String product){
		return CompletableFuture.supplyAsync(()->calculatePrice(product));
	}
	
	//工具方法：
	protected double calculatePrice(String product){
		Random random = new Random();
		//delay()
		CompleteDiscount.delay();
		return random.nextDouble()*product.charAt(0)+product.charAt(1);
	}
	//使用该方法模拟耗时的操作
	public static void delay(){
		try {
			Thread.sleep(1000L);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
