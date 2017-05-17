package java8.parallel;

import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * streamʵ�ֲ��������ܲ��ԣ�����ǰn�����ĺ�
 * ע�⣺��������С�����ݼ���ʹ�ô���ķ���ʱ���ܿ���Ҫ������Ĳ�
 * 		Ҫ���⹲��ɱ�״̬��ȷ����������������Ľ��
 *
 */
public class TestParallel {
	//ʹ����ͨѭ����
	public static int iteratorSum(int n){
		int sum = 0;
		for(int i=0;i<n;i++){
			sum+=i;
		}
		return sum;
	}
	//ʹ��˳������
	public static int sequentialSum(int n){
		return IntStream.range(0, n).sum();
	}
	//ʹ�ò�������
	public static int parallelSum(int n){
		return IntStream.range(0, n).parallel().reduce(0, (a,b)->a+b);
	}
	@Test//�������ܣ�
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



















