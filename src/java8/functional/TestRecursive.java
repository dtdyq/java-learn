package java8.functional;

import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 使用迭代还是递归？
 *
 */
public class TestRecursive {
	public static int factor1(int n){
		int res = 1;
		for(int i=2;i<=n;i++){
			res *= i;
		}
		return res;
	}
	public static int factor2(int n){
		return n == 1?1:factor2(n-1);
	}
	public static int factor3(int n){
		return factors(1,n);
	}
	private static int factors(int acc,int n){
		return n == 1?acc:factors(acc*n,n-1);
	}
	public static int factor4(int n){
		return IntStream.rangeClosed(0, n)
				.reduce(1, (x,y)->x*y);
	}
	public static int factor5(int n){
		return IntStream.rangeClosed(0, n).parallel()
				.reduce(1, (x,y)->x*y);
	}
	@Test
	public void test(){
		testFactor(1,10000);
		testFactor(2,10000);
		testFactor(3,10000);
		testFactor(4,10000);
		testFactor(5,10000);
	}
	public static void testFactor(int index,int rand){
		long t = System.currentTimeMillis();
		while(rand--!=0){
			switch(index){
				case 1:factor1(10000);
				case 2:factor2(10000);
				case 3:factor3(10000);
				case 4:factor4(10000);
				case 5:factor5(10000);
			}
		}
		System.out.println((System.currentTimeMillis()-t));
	}
	
}











