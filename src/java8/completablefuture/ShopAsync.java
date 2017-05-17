package java8.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ʹ��CompletableFutureʵ���첽API����
 * 
 * ����һ����Ѽ۸��ѯ������ѯ����̵꣬���ݸ����Ĳ�Ʒ�ͷ����ҳ���͵ļ۸�
 * 
 * ��һ����ShopNoBlock
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
		//���ڿ���ͬ��ִ����������
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
	//ͬ���Ļ�ȡ��Ʒ�۸�ķ���
	public double getPrice(String product){
		return calculatePrice(product);
	}
	//�첽�ķ�����
	public Future<Double> getPriceAsync(String product){
		CompletableFuture<Double> cf = new CompletableFuture<>();
		new Thread(()->{
			double d = calculatePrice(product);
			cf.complete(d);
		}).start();
		return cf;
	}
	//�����첽�����ĸĽ����߳��п��ܳ����쳣��Ӧ��ʹ��CompletableFuture���쳣�׳�
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
	//ʹ��CompletableFuture�Ĺ��������������ʵ����������
	public Future<Double> getPriceAsyncFactory(String product){
		return CompletableFuture.supplyAsync(()->calculatePrice(product));
	}
	
	//���߷�����
	protected double calculatePrice(String product){
		Random random = new Random();
		//delay()
		CompleteDiscount.delay();
		return random.nextDouble()*product.charAt(0)+product.charAt(1);
	}
	//ʹ�ø÷���ģ���ʱ�Ĳ���
	public static void delay(){
		try {
			Thread.sleep(1000L);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
