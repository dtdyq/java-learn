package thread;

public class ReadWrite {
	private Reader r=new Reader();
    private Writer w=new Writer();
	private static int page=10;
	
	private class Reader extends Thread{
		public void run(){
			try {
				read();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public void read() throws InterruptedException{
			while(true){
				if(page<0){
					Thread.sleep(1000);
				}
				else{
					System.out.println("read--->"+(--page));		
					Thread.sleep(10);

				}
			}
		}
	}
	private class Writer extends Thread{
		public void run(){
			try {
				Write();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		public  void Write() throws InterruptedException{
			while(true){
				if(page>20){
					Thread.sleep(1000);			
				}
				else{
					System.out.println("write--->"+(++page));
					Thread.sleep(10);
				}
			}
		}
	}
	public static void main(String[] args) {
		ReadWrite rw=new ReadWrite();
		rw.r.start();
		rw.w.start();
	}



}
