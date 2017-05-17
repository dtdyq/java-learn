package jvm.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dtdyq
 * 
 * 基于jmx的可视化监视、管理工具
 *
 */
public class Tools_JConsole {
	//死锁代码：
	static class SynAddRunnable implements Runnable{
		int a,b;
		public SynAddRunnable(int a,int b){
			this.a = a;
			this.b = b;
		}
		@Override
		public void run() {
			synchronized(Integer.valueOf(a)){
				synchronized(Integer.valueOf(b)){
					System.out.println(a+b);
				}
			}
		}
		
	}
	/**
	 * 
	 * 参数：-Xmx100m -Xms100m -XX:+UseSerialGC
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException{
		//testWaitThread()
		testDeadLockThread();
	}
	public static void testDeadLockThread(){
		for(int i=0;i<100;i++){
			new Thread(new SynAddRunnable(1,2)).start();
			new Thread(new SynAddRunnable(2,1)).start();
		}
	}
	public static void testWaitThread() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		ThreadView.createBusyThread();
		br.readLine();
		Object o = new Object();
		ThreadView.createLockThread(o);
	}
	
}
class MemoryView{
	static class OOMObject{
		public byte[] placeHolder = new byte[64*1024];
	}
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList<>();
		for(int i=0;i<num;i++){
			Thread.sleep(50);
			list.add(new OOMObject());
		}
	}
}
//线程等待代码：比起io阻塞，线程忙等总会消耗大量CPU资源
class ThreadView{
	//线程死循环：
	public static void createBusyThread(){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true);
			}
			
		},"testBusyThread");
		thread.start();
	}
	public static void createLockThread(final Object lock){
		Thread thread = new Thread(new Runnable(){
			public void run(){
				synchronized(lock){
					try{
						lock.wait();
					}catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		},"testLockThread");
		thread.start();
	}
}










