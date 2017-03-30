package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TortoiseRabbit {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService  es=Executors.newFixedThreadPool(2);
		Race tortoise=new Race("乌龟",2000);
		Race rabbit=new Race("小兔子",500);
		Future<Integer> t1=es.submit(tortoise);
		Future<Integer> t2=es.submit(rabbit);
		Thread.sleep(10000);
		tortoise.setFlag(false);
		rabbit.setFlag(false);
		int r1=t1.get();
		int r2=t2.get();
		
		System.out.println("乌龟跑了"+r1+"步");
		System.out.println("小兔子跑了"+r2+"步");
		
		es.shutdownNow();

	}

}
class Race implements Callable<Integer>{
	private String name=null;
	private long time;
	private int step=0;
	private boolean flag=true;
	public Race(String name,long time){
		super();
		this.setName(name);
		this.time=time;
	}
	public void setFlag(boolean b){
		this.flag=b;
	}
	public int getStep(){
		return this.step;
	}
	@Override
	public Integer call() throws Exception {
		while(flag){
			Thread.sleep(time);
			step++;
		}
		return step;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}