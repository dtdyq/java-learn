package thread;

/**
 * join: 一个线程等待另一个线程完成的方法，当在某个程序执行流中调用其他线程的
 * join方法时调用线程将会被阻塞，直到被join加入的线程执行完为止
 */
public class JoinTest implements Runnable{

	public static void main(String[] args) throws InterruptedException {
		JoinTest jt=new JoinTest();
		Thread t=new Thread(jt);
		t.start();
		for(int i=0;i<100;i++){
			Thread.sleep(10);
			if(i==40){
				t.join();   //main线程被阻塞
			}
			System.out.println("main--->"+i);
		}
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			try {
				Thread.sleep(10);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("join--->"+i);
		}
		
	}

}
