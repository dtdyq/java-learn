package java8.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 
 * @author dtdyq
 * 
 * java7的forkjoin框架：计算给定范围的数的和
 *
 */
public class TestForkJoin {
	//使用普通循环：
	public static long iteratorSum(long n){
		long sum = 0;
		for(long i=0;i<=n;i++){
			sum+=i;
		}
		return sum;
	}
	@org.junit.Test
	public void Test(){
		long res = 0;
		long t1 = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			res = iteratorSum(100000);
		}
		System.out.println("iterator: "+(System.currentTimeMillis()-t1)+"-->"+res);
		
		long[] tmp = LongStream.rangeClosed(0, 100000).toArray();
		ForkJoinTask<Long> task = new NumericCompute(tmp);
		ForkJoinPool pool = new ForkJoinPool();
		t1 = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			res = pool.invoke(task);
		}
		System.out.println("NumericCompute: "+(System.currentTimeMillis()-t1)+"-->"+res);
	}
}
class NumericCompute extends RecursiveTask<Long>{
	private static final long serialVersionUID = 1L;
	private final long[] numeric;
	private final int start;
	private final int end;
	private final static long THRESHOLD = 10_00;
	public NumericCompute(long[] numeric){
		this(numeric,0,numeric.length);
	}
	public NumericCompute(long[] numeric,int start,int end){
		this.numeric = numeric;
		this.start = start;
		this.end = end;
	}
	@Override
	protected Long compute() {
		int len = end - start;
		if (len <= THRESHOLD){
			return computeSequential();
		}
		NumericCompute left = new NumericCompute(numeric,start,start+len/2);
		left.fork();
		NumericCompute right = new NumericCompute(numeric,start+len/2,end);
		long resr = right.compute();
		long resl = left.join();
		return resl + resr;
	}
	private long computeSequential(){
		long sum = 0;
		for(int i=start;i<end;i++){
			sum += numeric[i];
		}
		return sum;
	}
}











