package thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPoolExecutor {
	public static void main(String[] args){
		ScheduledThreadPoolExecutor exc = new ScheduledThreadPoolExecutor(5);
		MyTask task = new MyTask();
//		exc.scheduleAtFixedRate(task, 5, 2, TimeUnit.SECONDS);
//		exc.scheduleWithFixedDelay(task, 3, 2, TimeUnit.SECONDS);
		exc.schedule(task,3,TimeUnit.SECONDS);
		exc.shutdown();
	}
}
class MyTask implements Runnable{
	private static int counter=0;
	@SuppressWarnings("unused")
	private final int id=counter++;
	public void run(){
//		while(!Thread.interrupted()){
			System.out.println(this+" runing!");
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
	}
}