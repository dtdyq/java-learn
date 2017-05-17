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
 * ʹ��CompletableFuture��thenAccept����ʵ�ֶ���Ѽ۸��ѯ�����Ż�
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
	//�ع�findPrices:
	public static Stream<CompletableFuture<String>> findPricesComplete(String product){
		//�����̳߳أ���һ���������߳��������ڶ����������崴���̵߳ķ�ʽ
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
				//����һ��Futureִ�еĽ����Ϊ�������ݸ��ڶ������� 
				.map(future -> future.thenCompose(quote -> 
					CompletableFuture.supplyAsync(
							()->Discount.applyDiscount(quote),executors
							)
						));
	}
	@Test
	public void test(){
		//����findPricesStream��
		CompletableFuture<?>[] futures = findPricesComplete("my favorite pro")
				.map(f->f.thenAccept(System.out::print))
				.toArray(size->new CompletableFuture[size]);
		CompletableFuture.allOf(futures).join();
		
	}

}
//����ʵ��Discount
class CompleteDiscount extends Discount{
	public static String applyDiscount(Quote quote){
		return quote.getName()+" price is "+
				apply(quote.getPrice(),quote.getCode())+"\n";
	}
	private static double apply(double price,Code code){
		delay();
		return price*((100-code.percentage)/100);
	}
	//��ΪҪʹ��CompletableFutureʵ�����Ȳ�ѯ���Ľ�����ȷ��أ�
	//���в���ʹ��֮ǰ��delay��������Ϊ��ֻ��ģ��̶���ʱ�䣬����
	//���������ӳٷ���
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








