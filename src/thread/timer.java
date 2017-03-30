package thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class timer {

	public static void main(String[] args) {
		
		Timer t=new Timer("test");
		t.schedule(new TimerTask(){
			public void run() {
				System.out.println("so easy...");
				System.out.println("so difficult");
			}
			   
		},new Date(System.currentTimeMillis()+2000), 1000);

	}

}
