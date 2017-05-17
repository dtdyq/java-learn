package java8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import org.junit.Test;
/**
 * 
 * @author dtdyq
 * 
 * 实现CompletableFuture的无阻塞操作
 * 下一步见ShopStream：使用CompletableFuture实现流水线操作
 *
 */
public class ShopNoBlock {
	static List<Shop> list;
	static{
		list = Arrays.asList(
				new Shop("BestPrice"),
				new Shop("LetsSaveBig"),
				new Shop("MyfavoriteShop"),
				new Shop("BuyItAll"),
				new Shop("JustBuy"),
				new Shop("BestPrice"),
				new Shop("LetsSaveBig"),
				new Shop("MyfavoriteShop"),
				new Shop("BuyItAll"),
				new Shop("JustBuy"),
				new Shop("BestPrice"),
				new Shop("LetsSaveBig"),
				new Shop("MyfavoriteShop"),
				new Shop("BuyItAll"),
				new Shop("JustBuy"),
				new Shop("BestPrice"),
				new Shop("LetsSaveBig"),
				new Shop("MyfavoriteShop"),
				new Shop("BuyItAll"),
				new Shop("JustBuy")
				);
	}
	//给定产品，返回字符串列表，字符串中包含了商店和对应的该商品的价格：
	//阻塞式方法：
	public static List<String> findPrices(String product){
		return list.stream()
				.map(shop->String.format("%s's price is %.2f\n",
						shop.getName(),shop.getPrice(product)))
				.collect(Collectors.toList());
	}
	//使用并行流进行并行计算：
	public static List<String> findPricesParallel(String product){
		return list.stream().parallel()
				.map(shop->String.format("%s's price is %.2f\n",
						shop.getName(),shop.getPrice(product)))
				.collect(Collectors.toList());
	}
	//使用CompletableFuture发起异步请求：
	public static List<String> findPricesCompletable(String product){
		List<CompletableFuture<String>> futurePrices = list.stream()
				.map(shop->CompletableFuture.supplyAsync(
						()->String.format(
								"%s's price is %.2f\n",
								shop.getName(),shop.getPrice(product))))
				.collect(Collectors.toList());
		return futurePrices.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
	//正常情况下，CompletableFuture和并行流的性能不相上下，不过CompletableFuture有
	//更高的灵活性：比如指定使用的线程数，这样而已显著提高运行效率
	public static List<String> findPricesExecutors(String product){
		//创建线程池：第一个参数是线程数量，第二个参数定义创建线程的方式
		Executor executors = Executors.newFixedThreadPool(Math.min(list.size(), 100),
				new ThreadFactory(){
				public Thread newThread(Runnable r){
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
				});
		List<CompletableFuture<String>> futurePrices = list.stream()
				.map(shop->CompletableFuture.supplyAsync(
						()->String.format(
								"%s's price is %.2f\n",
								shop.getName(),shop.getPrice(product)),executors))
				.collect(Collectors.toList());
		return futurePrices.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
	@Test
	public void test(){
		//测试findPrices的正确性和性能：
		long start = System.nanoTime();
		findPrices("myPhone").stream()
		.forEach(System.out::print);
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(duration);
		
		//测试并行流时间和性能：速度相比阻塞时的几乎快了四倍
		long start1 = System.nanoTime();
		findPricesParallel("myPhone").stream()
		.forEach(System.out::print);
		long duration1 = (System.nanoTime() - start1) / 1_000_000;
		System.out.println(duration1);
		
		//测试CompletableFuture异步请求的性能：是并行流的两倍
		long start2 = System.nanoTime();
		findPricesCompletable("myPhone").stream()
		.forEach(System.out::print);
		long duration2 = (System.nanoTime() - start2) / 1_000_000;
		System.out.println(duration2);
		
		//在将商店的数量增加到5个时，后面两个方法的性能不相伯
		//仲，其实两者使用的是同类的线程池
		
		//使用自定义的执行器后的性能：将shop的数量设置为了20个：速度明显快了数倍
		long start3 = System.nanoTime();
		findPricesExecutors("myPhone").stream()
		.forEach(System.out::print);
		long duration3 = (System.nanoTime() - start3) / 1_000_000;
		System.out.println(duration3);
	}
}







