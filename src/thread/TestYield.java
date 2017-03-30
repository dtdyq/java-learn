package thread;

public class TestYield implements Runnable{

	public void run(){
		for(int i=0;i<1000;i++){
			System.out.println("yield--->"+i);
		}
	}
	
	public static void main(String[] args) {
		TestYield yt=new TestYield();
		Thread t=new Thread(yt);
		t.start();
		for(int i=0;i<1000;i++){
			if(i%50==0){
				Thread.yield();
			}
			System.out.println("main--->"+i);
		}

	}

}
