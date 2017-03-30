package thread;

public class Synchronized implements Runnable{
	
	private int counter=1;
	private boolean flag=true;
	public void run(){
		while(flag){
			test5();
		}
	}
	@SuppressWarnings("unused")
	private void test1(){
		if(counter<=10){
			System.out.println(Thread.currentThread().getName()+"抢到了第--->"+(counter++)+"张票");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			flag=false;
		}
	
	}
	@SuppressWarnings("unused")
	private synchronized void test2(){
		if(counter<=10){
			System.out.println(Thread.currentThread().getName()+"抢到了第--->"+(counter++)+"张票");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			flag=false;
		}
	}
	@SuppressWarnings("unused")
	private void test3(){
		synchronized(this){
			if(counter<=10){
				System.out.println(Thread.currentThread().getName()+"抢到了第--->"+(counter++)+"张票");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				flag=false;
			}
		}
	}
	//线程锁定不正确：
	@SuppressWarnings("unused")
	private void test4(){
		
			if(counter<=10){
				synchronized(this){
				System.out.println(Thread.currentThread().getName()+"抢到了第--->"+(counter++)+"张票");
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			else{
				flag=false;
			}
	}
	//锁定对象不正确：
	private void test5(){
		synchronized((Integer)counter){
			if(counter<=10){
				
				System.out.println(Thread.currentThread().getName()+"抢到了第--->"+(counter++)+"张票");
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			else{
				flag=false;
			}
		}
}
	public static void main(String[] args) {
		Synchronized s1=new Synchronized();

		Thread t1=new Thread(s1,"大仙");
		Thread t2=new Thread(s1,"江主席");
		Thread t3=new Thread(s1,"猪八戒");
		t1.start();
		t2.start();
		t3.start();
		

	}

}
