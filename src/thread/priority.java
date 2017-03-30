package thread;

public class priority implements Runnable{
	private int num=0;
	private boolean flag=true;
	public void stop(){
		this.flag=false;
	}
	@Override
	public void run() {
		while(flag){
			System.out.println(Thread.currentThread().getName()+"-->"+(num++));
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		
		priority p1=new priority();
		priority p2=new priority();
		Thread t1=new Thread(p1,"test1");
		Thread t2=new Thread(p2,"test2");
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
		Thread.sleep(50);
		p1.stop();
		p2.stop();
	}

	

}
