package java8.parallel;

import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * stream实现并行流性能测试：计算前n个数的和
 * 注意：并行流在小的数据集或使用错误的方法时性能可能要比想象的差
 * 		要避免共享可变状态，确保并行流产生错误的结果
 *
 */
public class TestParallel {
	//使用普通循环：
	public static int iteratorSum(int n){
		int sum = 0;
		for(int i=0;i<n;i++){
			sum+=i;
		}
		return sum;
	}
	//使用顺序流：
	public static int sequentialSum(int n){
		return IntStream.range(0, n).sum();
	}
	//使用并行流：
	public static int parallelSum(int n){
		return IntStream.range(0, n).parallel().reduce(0, (a,b)->a+b);
	}
	@Test//测试性能：
	public void testSum(){
		long t1 = System.currentTimeMillis();
		long res = 0;
		for(int i=0;i<10000;i++){
			res = iteratorSum(100000);
		}
		System.out.println("iterator: "+(System.currentTimeMillis()-t1)+"-->"+res);
		t1 = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			res = sequentialSum(100000);
		}
		System.out.println("sequential: "+(System.currentTimeMillis()-t1)+"-->"+res);
		t1 = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			res = parallelSum(100000);
		}
		System.out.println("parallelSum: "+(System.currentTimeMillis()-t1)+"-->"+res);
	}
}



















