package java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * stream练习：
 *
 */
public class StreamExample {
	static List<Transaction> list;
	static{
		Trader raoul = new Trader("Raoul","Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		list = Arrays.asList(
				new Transaction(brian,2011,300),
				new Transaction(raoul,2012,1000),
				new Transaction(raoul,2011,400),
				new Transaction(mario,2012,710),
				new Transaction(mario,2012,700),
				new Transaction(alan,2012,950)
				);
	}
	@Test
	public void Test1(){
		//找出2011年发生的所有交易，并按交易额排序：
		list.stream()
		.filter(e->e.getYear()==2011)
		.sorted(Comparator.comparing(Transaction::getValue))
		.collect(Collectors.toList())
		.forEach(System.out::println);
	}
	@Test
	public void Test2(){
		//交易员都在哪些不同的城市工作过：
		list.stream()
		.map(Transaction::getTrader)
		.map(Trader::getCity)
		.distinct()
		.collect(Collectors.toList())
		.forEach(System.out::println);
	}
	@Test
	public void Task3(){
		//查找所有来自剑桥的交易员并按姓名排序：
		list.stream()
		.map(Transaction::getTrader)
		.filter(e->e.getCity().equals("Cambridge"))
		.sorted(Comparator.comparing(Trader::getName))
		.distinct()
		.collect(Collectors.toList())
		.forEach(System.out::println);
	}
	@Test
	public void Task4(){
		//返回所有交易员的姓名字符串，并按字符排序
		list.stream()
		.map(Transaction::getTrader)
		.map(Trader::getName)
		.sorted()
		.distinct()
		.collect(Collectors.toList())
		.forEach(System.out::println);
	}
	@Test
	public void Task5(){
		//是否有交易员在米兰工作
		list.stream()
		.map(Transaction::getTrader)
		.map(Trader::getCity)
		.anyMatch(e->e.equals("milan"));
	}
	@Test
	public void Task6(){
		//打印生活在剑桥的交易员的所有交易金额
		int sum = list.stream()
		.filter(e->e.getTrader().getCity().equals("Cambridge"))
		.map(Transaction::getValue)
		.reduce(0,Integer::sum);
		System.out.println(sum);
	}
	@Test
	public void Task7(){
		//所有交易中最高的交易额是多少：
		Optional<Integer> max = list.stream()
				.map(Transaction::getValue)
				.max((x,y)->Integer.compare(x, y));
		System.out.println(max.get());
	}
	@Test
	public void Task8(){
		//找到交易额最小的交易对应的交易员：
        list.stream()
		.min(Comparator.comparing(Transaction::getValue))
		.ifPresent(e->{
			System.out.println(e.getTrader());
		});
	}
}
class Trader{
	private final String name;
	private final String city;
	public Trader(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	@Override
	public String toString() {
		return "Trader [name=" + name + ", city=" + city + "]";
	}	
}
class Transaction{
	private final Trader trader;
	private final int year;
	private final int value;
	public Transaction(Trader trader, int year, int value) {
		super();
		this.trader = trader;
		this.year = year;
		this.value = value;
	}
	public Trader getTrader() {
		return trader;
	}
	public int getYear() {
		return year;
	}
	public int getValue() {
		return value;
	}
	@Override
	public String toString() {
		return String.format("[trader=%s, year=%s, value=%s]",
				trader, year, value);
	}
	
}










