package thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//通过使用管道流实现生产者消费者模型
public class TestPipe {
	public static void main(String[] args) throws Exception{
		Sender sender=new Sender();
		new Thread(sender).start();
		Receiver receiver=new Receiver(sender);
		ExecutorService exec=Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}

}
class Sender implements Runnable{
	private Random random=new Random();
	private PipedWriter pw=new PipedWriter();
	public PipedWriter getPipedWriter(){
		return pw;
	}
	public void run(){
		while(true){
			for(char c='A';c<='z';++c){
				try {
					pw.write(c);
					TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
				} catch (IOException|InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
class Receiver implements Runnable{
	private PipedReader pr;
	public Receiver(Sender sender) throws Exception{
		pr=new PipedReader(sender.getPipedWriter());
	}
	public void run(){
		while(true){
			try {
				System.out.println("reading； "+(char)pr.read());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}