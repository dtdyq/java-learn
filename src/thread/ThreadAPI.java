package thread;

public class ThreadAPI implements Runnable{
	private boolean flag=true;
	private int counter=0;
	public void run(){
		while(flag){
			System.out.println(Thread.currentThread().getName()+(counter++));
		}
	}
	public void stop(){
		this.flag=false;
	}
	public static void main(String[] args) {
		ThreadAPI t=new ThreadAPI();
		Thread th=new Thread(t);
		th.setName("test");
		th.start();
		System.out.println(th.getName()+"--->"+th.isAlive());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.stop();
		for(int i=0;i<100000;i++);
		System.out.println(th.getName()+"--->"+th.isAlive());

	}

}
