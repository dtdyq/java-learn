package java8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
 * 使用CompletableFuture实现流水线操作
 * 
 * 这里的findPricesComplete方法需要等到所有的线程都执行完毕才返回，因为join方法会阻塞、
 * 线程，而有时候希望只要有完成的任务就马上返回已经完成的，CompletableFuture实现了这一
 * 功能，详见ShopCompletion
 *
 */
public class ShopStream {
	static List<NewShop> list;
	static{
		list = Arrays.asList(
				new NewShop("BestPrice"),
				new NewShop("LetsSaveBig"),
				new NewShop("MyfavoriteShop"),
				new NewShop("BuyItAll"),
				new NewShop("JustBuy"));
	}
	//获取商店名称和商店价格：
	public static List<String> findPrices(String product){
		return list.stream()
				.map(shop->shop.getPrices(product))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(Collectors.toList());
	}
	//构造同步和异步操作，实现更高效的操作：
	public static List<String> findPricesComplete(String product){
		//创建线程池：第一个参数是线程数量，第二个参数定义创建线程的方式
		Executor executors = Executors.newFixedThreadPool(Math.min(list.size(), 100),
				new ThreadFactory(){
				public Thread newThread(Runnable r){
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
				});
				
		List<CompletableFuture<String>> fp = list.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						()->shop.getPrices(product),executors
						))
				.map(future -> future.thenApply(Quote::parse))
				//将第一个Future执行的结果作为参数传递给第二个参数 
				.map(future -> future.thenCompose(quote -> 
					CompletableFuture.supplyAsync(
							()->Discount.applyDiscount(quote),executors
							)
						))
				.collect(Collectors.toList());
		return fp.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
	@Test
	public void test(){
		//测试findPrices的正确性和性能：用时十秒左右
		long start = System.nanoTime();
		findPrices("myPhone").stream()
		.forEach(System.out::print);
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(duration);
		
		//测试CompletableFuture性能：
		long start1 = System.nanoTime();
		findPrices("myPhone").stream()
		.forEach(System.out::print);
		long duration1 = (System.nanoTime() - start1) / 1_000_000;
		System.out.println(duration1);
	}
}
//假设每个商店都同意使用同一类折扣代码：
class Discount{

	public enum Code{
		NONE(0),SILVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);
		final int percentage;
		Code(int percentage){
			this.percentage = percentage;
		}
	}
	//接受Quote对象，返回一个字符串，表示该Quote对应的shop中的折扣价格：
	public static String applyDiscount(Quote quote){
		return quote.getName()+" price is "+
				apply(quote.getPrice(),quote.getCode())+"\n";
	}
	private static double apply(double price,Code code){
		NewShop.delay();
		return price*((100-code.percentage)/100);
	}
}
//重新定义商店：
class NewShop extends Shop{
	public NewShop(String name) {
		super(name);
	}
	public String getPrices(String product){
		Random random = new Random();
		double price = calculatePrice(product);
		Discount.Code code = Discount.
				Code.values()[random.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", name,price,code);
	}
}
//解析类：对商店返回的名字、价格和折扣码进行解析：
class Quote{
	private final String name;
	private final double price;
	private Discount.Code code;
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public Discount.Code getCode() {
		return code;
	}
	public Quote(String name,double price,Discount.Code code){
		this.name = name;
		this.price = price;
		this.code = code;
	}
	public static Quote parse(String pro){
		String[] tmp = pro.split(":");
		String name = tmp[0];
		double price = Double.parseDouble(tmp[1]);
		Discount.Code code = Discount.Code.valueOf(tmp[2]);
		return new Quote(name,price,code);
	}
}






