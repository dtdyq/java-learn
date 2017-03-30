package thread;

public class ProCom {
    private int counter=5;
    //counter<0-->�����������������ߵȴ���������������ɺ���������
    //counter>10-->���������ѣ������ߵȴ���������������ɺ���������
    
    public synchronized void product() throws InterruptedException{
    	if(counter>10){
    		this.wait();
    	}
    	else{
    		Thread.sleep(0);
        	System.out.println("����������-->"+(++counter));	
        	this.notifyAll();
    	}
    }  
    public synchronized void consume() throws InterruptedException{
    	if(counter<=0){
    		this.wait();
    	}
    	else{
    		Thread.sleep(0);
    		System.out.println("����������-->"+(--counter));
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





