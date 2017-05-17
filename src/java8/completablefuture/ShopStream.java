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
 * ʹ��CompletableFutureʵ����ˮ�߲���
 * 
 * �����findPricesComplete������Ҫ�ȵ����е��̶߳�ִ����ϲŷ��أ���Ϊjoin������������
 * �̣߳�����ʱ��ϣ��ֻҪ����ɵ���������Ϸ����Ѿ���ɵģ�CompletableFutureʵ������һ
 * ���ܣ����ShopCompletion
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
	//��ȡ�̵����ƺ��̵�۸�
	public static List<String> findPrices(String product){
		return list.stream()
				.map(shop->shop.getPrices(product))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(Collectors.toList());
	}
	//����ͬ�����첽������ʵ�ָ���Ч�Ĳ�����
	public static List<String> findPricesComplete(String product){
		//�����̳߳أ���һ���������߳��������ڶ����������崴���̵߳ķ�ʽ
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
				//����һ��Futureִ�еĽ����Ϊ�������ݸ��ڶ������� 
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
		//����findPrices����ȷ�Ժ����ܣ���ʱʮ������
		long start = System.nanoTime();
		findPrices("myPhone").stream()
		.forEach(System.out::print);
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(duration);
		
		//����CompletableFuture���ܣ�
		long start1 = System.nanoTime();
		findPrices("myPhone").stream()
		.forEach(System.out::print);
		long duration1 = (System.nanoTime() - start1) / 1_000_000;
		System.out.println(duration1);
	}
}
//����ÿ���̵궼ͬ��ʹ��ͬһ���ۿ۴��룺
class Discount{

	public enum Code{
		NONE(0),SILVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);
		final int percentage;
		Code(int percentage){
			this.percentage = percentage;
		}
	}
	//����Quote���󣬷���һ���ַ�������ʾ��Quote��Ӧ��shop�е��ۿۼ۸�
	public static String applyDiscount(Quote quote){
		return quote.getName()+" price is "+
				apply(quote.getPrice(),quote.getCode())+"\n";
	}
	private static double apply(double price,Code code){
		NewShop.delay();
		return price*((100-code.percentage)/100);
	}
}
//���¶����̵꣺
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
//�����ࣺ���̵귵�ص����֡��۸���ۿ�����н�����
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






