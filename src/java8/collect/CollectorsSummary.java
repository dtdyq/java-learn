package java8.collect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * collectors总结：
 * toList()     List<T>
 * toSet()      Set<T>
 * toCollection Collection<T>
 * counting     long
 * summingInt   Integer
 * averagingInt double
 * summarizingInt     IntSummaryStatistics
 * join         String
 * maxBy         Option<T>
 * minBy         OPtion<T>
 * reducing      归约操作产生的类型
 * collectionAndThan    对收集到的结果进行转换
 * groupingBy      分组
 * partitioningBy    分区
 * 
 * 以下为一个例子：对之前求一定范围的质数的例子的改进：使用了自己定义的收集器
 *
 */
public class CollectorsSummary {
	public Map<Boolean,List<Integer>> getPrime(int candidate){
		return IntStream.range(2, candidate)
				.boxed()
				.collect(new PrimeNumericCollector());
	}
	@Test
	public void testPrime(){
		List<Integer> primes = getPrime(100).get(true);
		primes.forEach(System.out::println);
	}
	
}
//自定义收集器：
class PrimeNumericCollector implements Collector<Integer,Map<Boolean,List<Integer>>,
						Map<Boolean,List<Integer>>>{
	//返回一个在调用时创建累加器的函数
	@SuppressWarnings("serial")
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return ()->
			new HashMap<Boolean,List<Integer>>(){{
				put(true,new ArrayList<Integer>());
				put(false,new ArrayList<Integer>());
			}};
	}
	//定义如何收集流中的元素
	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (acc,candidate)->{
			acc.get(isPrime(acc.get(true),candidate))
			.add(candidate);
		};
	}
	//将两个收集器合并起来：
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (a,b)->{
			a.get(true).addAll(b.get(true));
			a.get(false).addAll(b.get(false));
			return a;
		};
	}
	//定义最后的返回类型转换函数，这里不需要转换：
	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}
	//定义收集器的行为
	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(
				EnumSet.of(Characteristics.IDENTITY_FINISH));
	}
	//给定一个排序列表，返回元素满足谓词的最长前缀：
	public static <A> List<A> takeWhile(List<A> list,Predicate<A> p){
		int i= 0;
		for(A item:list){
			if(!p.test(item)){
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}
	//判断是否为质数：
	public static boolean isPrime(List<Integer> list,int candidate){
		int candidateRoot = (int) Math.sqrt(candidate);
		return takeWhile(list,i->i<=candidateRoot)
				.stream().noneMatch(p->candidate%p==0);
	}
}









