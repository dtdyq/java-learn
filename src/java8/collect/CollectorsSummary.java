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
 * collectors�ܽ᣺
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
 * reducing      ��Լ��������������
 * collectionAndThan    ���ռ����Ľ������ת��
 * groupingBy      ����
 * partitioningBy    ����
 * 
 * ����Ϊһ�����ӣ���֮ǰ��һ����Χ�����������ӵĸĽ���ʹ�����Լ�������ռ���
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
//�Զ����ռ�����
class PrimeNumericCollector implements Collector<Integer,Map<Boolean,List<Integer>>,
						Map<Boolean,List<Integer>>>{
	//����һ���ڵ���ʱ�����ۼ����ĺ���
	@SuppressWarnings("serial")
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return ()->
			new HashMap<Boolean,List<Integer>>(){{
				put(true,new ArrayList<Integer>());
				put(false,new ArrayList<Integer>());
			}};
	}
	//��������ռ����е�Ԫ��
	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (acc,candidate)->{
			acc.get(isPrime(acc.get(true),candidate))
			.add(candidate);
		};
	}
	//�������ռ����ϲ�������
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (a,b)->{
			a.get(true).addAll(b.get(true));
			a.get(false).addAll(b.get(false));
			return a;
		};
	}
	//�������ķ�������ת�����������ﲻ��Ҫת����
	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}
	//�����ռ�������Ϊ
	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(
				EnumSet.of(Characteristics.IDENTITY_FINISH));
	}
	//����һ�������б�����Ԫ������ν�ʵ��ǰ׺��
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
	//�ж��Ƿ�Ϊ������
	public static boolean isPrime(List<Integer> list,int candidate){
		int candidateRoot = (int) Math.sqrt(candidate);
		return takeWhile(list,i->i<=candidateRoot)
				.stream().noneMatch(p->candidate%p==0);
	}
}









