package java8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 使用CompletableFuture的thenAccept方法实现对最佳价格查询器的优化
 * 
 * 
 *
 */
public class ShopCompletion {
	static List<NewShop> list;
	static{
		list = Arrays.asList(
				new NewShop("BestPrice"),
				new NewShop("LetsSaveBig"),
				new NewShop("MyfavoriteShop"),
				new NewShop("BuyItAll"),
				new NewShop("JustBuy"));
	}
	//重构findPrices:
	public static Stream<CompletableFuture<String>> findPricesComplete(String product){
		//创建线程池：第一个参数是线程数量，第二个参数定义创建线程的方式
		Executor executors = Executors.newFixedThreadPool(Math.min(list.size(), 100),
				new ThreadFactory(){
				public Thread newThread(Runnable r){
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
				});
		return list.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						()->shop.getPrices(product),executors
						))
				.map(future -> future.thenApply(Quote::parse))
				//将第一个Future执行的结果作为参数传递给第二个参数 
				.map(future -> future.thenCompose(quote -> 
					CompletableFuture.supplyAsync(
							()->Discount.applyDiscount(quote),executors
							)
						));
	}
	@Test
	public void test(){
		//测试findPricesStream：
		CompletableFuture<?>[] futures = findPricesComplete("my favorite pro")
				.map(f->f.thenAccept(System.out::print))
				.toArray(size->new CompletableFuture[size]);
		CompletableFuture.allOf(futures).join();
		
	}

}
//重新实现Discount
class CompleteDiscount extends Discount{
	public static String applyDiscount(Quote quote){
		return quote.getName()+" price is "+
				apply(quote.getPrice(),quote.getCode())+"\n";
	}
	private static double apply(double price,Code code){
		delay();
		return price*((100-code.percentage)/100);
	}
	//因为要使用CompletableFuture实现最先查询到的结果最先返回，
	//所有不再使用之前的delay方法，因为它只能模拟固定的时间，改用
	//下面的随机延迟方法
	public static void delay(){
		Random random = new Random();
		int delay = 1000 + random.nextInt(3000);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}








