package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *sleep:
 *1°¢µπº∆ ±
 *2°¢Õ¯¬Á—” ±
 * @author dtdyq
 *
 */
public class Sleep {
	public static void time(int t) throws InterruptedException{
		int num=t;
		while(true){
			System.out.println(num--);
			Thread.sleep(1000);
			if(num<0){
				break;
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		//time(4);
		Date endTime=new Date(System.currentTimeMillis());
		long end=endTime.getTime();
		while(true){
			System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
			endTime=new Date(endTime.getTime()-1000);
			Thread.sleep(1000);
			if(end-10000>endTime.getTime()){
				break;
			}
		}

	}

}








