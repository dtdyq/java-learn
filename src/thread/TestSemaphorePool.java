package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphorePool {
	public static void main(String[] args) throws Exception{
		Pool<Fat> pool=new Pool<>(Fat.class,3);
		Fat fat=null;
		for(int i=0;i<2;i++){
			fat = (Fat) pool.checkOut();
		}
		fat.operation();
		TimeUnit.SECONDS.sleep(1000);
		pool.checkIn(fat);
		Fat f=(Fat)pool.checkOut();
		f.operation();
		
	}
}

class Fat{
	private volatile double d;
	private static int counter=0;
	private final int id=counter++;
	public Fat(){
		for(int i=1;i<10000;i++){
			d+=(Math.PI+Math.E)/(double)i;
		}
	}
	public void operation(){
		System.out.println(this+":"+d);
	}
	public String toString(){
		return "Fat id: "+id;
	}
}
class Pool<T>{
	private static int size;
	private List<T> items = new ArrayList<>();
	private volatile boolean[] checkout;
	private Semaphore available;
	public Pool(Class<T> classobj,int size) throws Exception{
		Pool.size=size;
		checkout=new boolean[size];
		available = new Semaphore(size,true);
		for(int i=0;i<size;i++){
			items.add(classobj.newInstance());
		}
	}
	public T checkOut() throws InterruptedException{
		available.acquire();
		return getItem();
	}
	private synchronized T getItem(){
		for(int i=0;i<size;i++){
			if(!checkout[i]){
				checkout[i]=true;
				return items.get(i);
			}
		}
		return null;
	}
	public void checkIn(T t){
		if(releaseItem(t)){
			available.release();
		}
	}
	private synchronized boolean releaseItem(T item){
		int index=items.indexOf(item);
		if(index==-1){
			return false;
		}
		if(checkout[index]){
			checkout[index]=false;
			return true;
		}
		return false;
	}
}
