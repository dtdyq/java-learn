package thread;
import java.util.*;
import java.io.*;
public class TimerTest extends TimerTask{
	public void run(){
		File file=new File("C:/Users/dtdyq/Desktop/test");
		delete(file);
	}
	private static void delete(File file){
		File[] files=file.listFiles();
		if(files!=null){
			for(File f:files){
				if(f.isDirectory()){
					delete(f);
				}
				else{
					System.out.println(f.getName()+"-->"+f.delete());
				}
			}
			System.out.println(file.getName()+":"+file.delete());
		}
	}
	public static void main(String[] args){
		Timer t=new Timer();
		t.schedule(new TimerTest(), new Date(System.currentTimeMillis()+2000));
		t.cancel();
	}
}
