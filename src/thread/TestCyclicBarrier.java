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
 * һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ� (common barrier point)��
 * ���漰һ��̶���С���̵߳ĳ����У���Щ�̱߳��벻ʱ�ػ���ȴ�����ʱ CyclicBarrier �����á�
 * ��Ϊ�� barrier ���ͷŵȴ��̺߳�������ã����Գ���Ϊѭ�� �� barrier��CyclicBarrier
 *  ֧��һ����ѡ�� Runnable �����һ���߳��е����һ���̵߳���֮�󣨵����ͷ������߳�֮ǰ
 *  ����������ֻ��ÿ�����ϵ�����һ�Ρ����ڼ������в����߳�֮ǰ���¹���״̬�������ϲ��� ����
 *  �á� 
	��Ҫ������
	
	//����parties��count��barrierCommand���ԡ�   
	CyclicBarrier(int):   
	  
	//��await�������������趨������������ִ�и�Runnable����   
	CyclicBarrier(int,Runnable):   
	  
	//֪ͨbarrier������߳�   
	await():  
	//����parties��count��barrierCommand���ԡ�
	CyclicBarrier(int):
	
	//��await�������������趨������������ִ�и�Runnable����
	CyclicBarrier(int,Runnable):
	
	//֪ͨbarrier������߳�
	await():
	
	
	Ӧ�ó��� 
	��ĳ�������У�����һ�����͵����񣬳�����Ҫ����ö�������ȥִ�У�
	ֻ�е�����������ִ�����ʱ�򣬲���ִ����������ʱ�򣬾Ϳ���ѡ��CyclicBarrier�ˡ� 
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














