package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 所有等待在CountDownLatch上的线程都将发生阻塞，直到该CountDownLatch的count减为0
 * @author Admin
 *
 */
public class TestCountDownLatch {
	static final int SIZE=100;
	public static void main(String[] args){
		ExecutorService exec=Executors.newCachedThreadPool();
		CountDownLatch latch=new CountDownLatch(SIZE);
		for(int i=0;i<10;i++){
			exec.execute(new WaitingTask(latch));
		}
		for(int i=0;i<SIZE;++i){
			exec.execute(new TaskPortion(latch));
		}
		System.out.println("launched all tasks");
		exec.shutdown();
		
	}
}
class TaskPortion implements Runnable{
	private static int counter=0;
	private final int id=counter++;
	private static Random random=new Random(47);
	private final CountDownLatch latch;
	TaskPortion(CountDownLatch latch){
		this.latch=latch;
	}
	public void run(){
		try {
			doWork();
			latch.countDown();
			System.out.println(this+" completed");
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	private void doWork() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
	}
	public String toString(){
		return String.format("%1$-3d", id);
	}
}
class WaitingTask implements Runnable{
	private static int counter=0;
	private final int id=counter++;
	private final CountDownLatch latch;
	WaitingTask(CountDownLatch latch){
		this.latch=latch;
	}
	public void run(){
		try{
			latch.await();
			System.out.println("latch barrier passed for "+this);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public String toString(){
		return String.format("WaitingTask %1$-3d", id);
	}
}











