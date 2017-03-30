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
			System.out.println(Thread.currentThread().getName()+"�����˵�--->"+(counter++)+"��Ʊ");
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
			System.out.println(Thread.currentThread().getName()+"�����˵�--->"+(counter++)+"��Ʊ");
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
				System.out.println(Thread.currentThread().getName()+"�����˵�--->"+(counter++)+"��Ʊ");
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
	//�߳���������ȷ��
	@SuppressWarnings("unused")
	private void test4(){
		
			if(counter<=10){
				synchronized(this){
				System.out.println(Thread.currentThread().getName()+"�����˵�--->"+(counter++)+"��Ʊ");
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
	//����������ȷ��
	private void test5(){
		synchronized((Integer)counter){
			if(counter<=10){
				
				System.out.println(Thread.currentThread().getName()+"�����˵�--->"+(counter++)+"��Ʊ");
				
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

		Thread t1=new Thread(s1,"����");
		Thread t2=new Thread(s1,"����ϯ");
		Thread t3=new Thread(s1,"��˽�");
		t1.start();
		t2.start();
		t3.start();
		

	}

}
