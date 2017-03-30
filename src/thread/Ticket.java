package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
	public static void main(String[] args){
//		Sell s1=new Sell();
//		Sell s2=new Sell();
//		Sell s3=new Sell();
//		s1.setName("窗口1");
//		s2.setName("窗口2");
//		s3.setName("窗口3");
//		s1.start();
//		s2.start();
//		s3.start();
		Sells s=new Sells();
		Thread t1=new Thread(s,"frist");
		Thread t2=new Thread(s,"second");
		Thread t3=new Thread(s,"third");
		t1.start();
		t2.start();
		t3.start();
	}
}
class Sell extends Thread{
	private static int tickets=1;
	public void run(){
		sells();
	}
	private synchronized void sells(){
		while(true){
			if(tickets<=100){
				System.out.println("卖出了第"+tickets+"张票。");
				tickets++;
			}
		}
	}
}
class Sells implements Runnable{
	private int tickets=1;
	private boolean flag=true;
	private Lock l=new ReentrantLock();
	@Override
	public void run() {
		while(true){
			l.lock();
			if(flag){
				flag=false;
			l.unlock();
			if(tickets<=100){
				System.out.println(Thread.currentThread().getName()+"卖出了第"+tickets+"张票。");
				tickets++;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			flag=true;
			}
			
			
		}
	}
	@SuppressWarnings("unused")
	private synchronized void sells(){
		if(tickets<=100){
			System.out.println(Thread.currentThread().getName()+"卖出了第"+tickets+"张票。");
			tickets++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}