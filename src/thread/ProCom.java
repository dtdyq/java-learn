package thread;

public class ProCom {
    private int counter=5;
    //counter<0-->生产者生产，消费者等待，生产者生产完成后唤醒消费者
    //counter>10-->消费者消费，生产者等待，消费者消费完成后唤醒生产者
    
    public synchronized void product() throws InterruptedException{
    	if(counter>10){
    		this.wait();
    	}
    	else{
    		Thread.sleep(0);
        	System.out.println("生产者生产-->"+(++counter));	
        	this.notifyAll();
    	}
    }  
    public synchronized void consume() throws InterruptedException{
    	if(counter<=0){
    		this.wait();
    	}
    	else{
    		Thread.sleep(0);
    		System.out.println("消费者消费-->"+(--counter));
    		this.notifyAll();
    	}  	
    }
	public static void main(String[] args) {
		
		ProCom k=new ProCom();
		producer p=new producer(k);
		consumer c=new consumer(k);
		p.start();
		c.start();
	}
}
class producer extends Thread{
	ProCom k=null;
	public producer(ProCom g){
		this.k=g;
	}
	public void run(){
		while(true){
			try {
				k.product();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
class consumer extends Thread{
	ProCom k=null;
	public consumer(ProCom g){
		this.k=g;
	}
	public void run(){
		while(true){
			try {
				k.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}
}





