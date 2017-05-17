package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。CyclicBarrier
 *  支持一个可选的 Runnable 命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前
 *  ），该命令只在每个屏障点运行一次。若在继续所有参与线程之前更新共享状态，此屏障操作 很有
 *  用。 
	主要方法：
	
	//设置parties、count及barrierCommand属性。   
	CyclicBarrier(int):   
	  
	//当await的数量到达了设定的数量后，首先执行该Runnable对象。   
	CyclicBarrier(int,Runnable):   
	  
	//通知barrier已完成线程   
	await():  
	//设置parties、count及barrierCommand属性。
	CyclicBarrier(int):
	
	//当await的数量到达了设定的数量后，首先执行该Runnable对象。
	CyclicBarrier(int,Runnable):
	
	//通知barrier已完成线程
	await():
	
	
	应用场景 
	在某种需求中，比如一个大型的任务，常常需要分配好多子任务去执行，
	只有当所有子任务都执行完成时候，才能执行主任务，这时候，就可以选择CyclicBarrier了。 
 * @author dtdyq
 *
 */
public class TestCyclicBarrier {
	public static void main(String[] args){
		new HorseRace(7,200);
	}

}
class Horse implements Runnable{
	private static int counter=0;
	private final int id=counter++;
	private int strides=0;
	private static Random random=new Random(47);
	private static CyclicBarrier barrier;
	public int getStrides(){
		return this.strides;
	}
	@SuppressWarnings("static-access")
	public Horse(CyclicBarrier b){
		this.barrier=b;
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				synchronized(this){
					strides+=random.nextInt(5);
					barrier.await();
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(BrokenBarrierException ee){
			ee.printStackTrace();
		}
	}
	public String toString(){
		return "Horse "+id;
	}
	public String tracks(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<strides;i++){
			sb.append("*");
		}
		return sb.append(id).toString();
	}
}
class HorseRace{
	static final int LINE=75;
	private List<Horse> horses=new ArrayList<>();
	private ExecutorService exc=Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	public HorseRace(int nHorses,final int pause){
		barrier = new CyclicBarrier(nHorses,new Runnable(){
			
			@Override
			public void run() {
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<LINE;i++){
					sb.append("=");
				}
				System.out.println(sb);
				for(Horse h:horses){
					System.out.println(h.tracks());
				}
				for(Horse h:horses){
					if(h.getStrides()>=LINE){
						System.out.println(h+" win!");
						exc.shutdownNow();
					}
				}
				try{
					TimeUnit.MILLISECONDS.sleep(pause);
				}catch(InterruptedException e){
					System.out.println("barrier action sleep interrupted");
				}
			}
			
		});
		for(int i=0;i<nHorses;i++){
			Horse horse=new Horse(barrier);
			horses.add(horse);
			exc.execute(horse);
		}
	}
}














