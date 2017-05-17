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
 * ʵ��CompletableFuture������������
 * ��һ����ShopStream��ʹ��CompletableFutureʵ����ˮ�߲���
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
	//������Ʒ�������ַ����б��ַ����а������̵�Ͷ�Ӧ�ĸ���Ʒ�ļ۸�
	//����ʽ������
	public static List<String> findPrices(String product){
		return list.stream()
				.map(shop->String.format("%s's price is %.2f\n",
						shop.getName(),shop.getPrice(product)))
				.collect(Collectors.toList());
	}
	//ʹ�ò��������в��м��㣺
	public static List<String> findPricesParallel(String product){
		return list.stream().parallel()
				.map(shop->String.format("%s's price is %.2f\n",
						shop.getName(),shop.getPrice(product)))
				.collect(Collectors.toList());
	}
	//ʹ��CompletableFuture�����첽����
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
	//��������£�CompletableFuture�Ͳ����������ܲ������£�����CompletableFuture��
	//���ߵ�����ԣ�����ָ��ʹ�õ��߳������������������������Ч��
	public static List<String> findPricesExecutors(String product){
		//�����̳߳أ���һ���������߳��������ڶ����������崴���̵߳ķ�ʽ
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
		//����findPrices����ȷ�Ժ����ܣ�
		long start = System.nanoTime();
		findPrices("myPhone").stream()
		.forEach(System.out::print);
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(duration);
		
		//���Բ�����ʱ������ܣ��ٶ��������ʱ�ļ��������ı�
		long start1 = System.nanoTime();
		findPricesParallel("myPhone").stream()
		.forEach(System.out::print);
		long duration1 = (System.nanoTime() - start1) / 1_000_000;
		System.out.println(duration1);
		
		//����CompletableFuture�첽��������ܣ��ǲ�����������
		long start2 = System.nanoTime();
		findPricesCompletable("myPhone").stream()
		.forEach(System.out::print);
		long duration2 = (System.nanoTime() - start2) / 1_000_000;
		System.out.println(duration2);
		
		//�ڽ��̵���������ӵ�5��ʱ�������������������ܲ��ಮ
		//�٣���ʵ����ʹ�õ���ͬ����̳߳�
		
		//ʹ���Զ����ִ����������ܣ���shop����������Ϊ��20�����ٶ����Կ�������
		long start3 = System.nanoTime();
		findPricesExecutors("myPhone").stream()
		.forEach(System.out::print);
		long duration3 = (System.nanoTime() - start3) / 1_000_000;
		System.out.println(duration3);
	}
}







