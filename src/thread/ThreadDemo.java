package thread;

import java.util.Scanner;

public class ThreadDemo {
	public static void main(String[] args) throws InterruptedException{
//		Threadd td=new Threadd();
//		td.start();
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		td.interrupt();
//		td.stop();
//		Threadb tb=new Threadb();
//		tb.start();
//		Thread.sleep(3000);
////		tb.stop();
//		tb.interrupt();

		Threadf tf=new Threadf();
		tf.start();
		Threadf df=new Threadf();
		df.start();
//		while(true){
//			Scanner sc=new Scanner(System.in);
//			String str=sc.nextLine();
//			System.out.println(Thread.currentThread().getName()+":"+str);
//		}
	}
}
class Threadd extends Thread{
	@Override
	public void run(){
		for(int i=0;i<1000;i++){
			System.out.println(getName()+":"+i);
		}
	}
}
class Threadb extends Thread{
	@Override
	public void run(){
		System.out.println("start");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("stop");
	}
}
class Threadf extends Thread{
	
	@Override
	public void run(){
		while(true){
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			Thread.yield();
			String str=sc.nextLine();
			System.out.println(getName()+":"+str);
		}
	}
}