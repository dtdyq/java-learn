package thread;

import java.util.concurrent.*;

public class TestCallable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es= Executors.newFixedThreadPool(10);

		long t1=System.currentTimeMillis();
		Future<Integer> f1=es.submit(new MyCall(0,1000));
		Future<Integer> f2=es.submit(new MyCall(1001,2000));
		System.out.println(f1.get()+f2.get());
		es.shutdown();
		System.out.println(System.currentTimeMillis()-t1);
		long t2=System.currentTimeMillis();
		int sum=0;
		for(int i=0;i<=2000;i++){
			sum+=i;
		}
		System.out.println(sum);
		System.out.println(System.currentTimeMillis()-t2);

	}
}


class MyCall implements Callable<Integer> {
	private int number;
	private int beg;
	public MyCall(int beg,int number){
		this.beg=beg;
		this.number=number;
	}
	public Integer call(){
		int sum=0;
		for(int x=beg;x<=number;x++){
			sum+=x;
		}
		return sum;
	}
}