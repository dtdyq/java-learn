package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import thread.NoodleProcess.Status;

public class TestMultiBlockingQueue {
	public static void main(String[] args) throws InterruptedException{
		NoodlerQueue pasteQueue=new NoodlerQueue(),
		noodleQueue=new NoodlerQueue(),
		ripeQueue=new NoodlerQueue();
		ExecutorService exec=Executors.newFixedThreadPool(4);
		exec.execute(new Paster(pasteQueue));
		exec.execute(new Noodler(pasteQueue,noodleQueue));
		exec.execute(new Riper(noodleQueue,ripeQueue));
		exec.execute(new Eater(ripeQueue));
		TimeUnit.SECONDS.sleep(30);
		exec.shutdownNow();
	}
	
	
}
//noodle process
class NoodleProcess{
	public enum Status{
		Flour,Paste,Noodle,Ripe
	}
	private Status status=Status.Flour;
    private int id;
    public NoodleProcess(int id){
    	this.id=id;
    }
    public Status getStatus(){
    	return status;
    }
    public void setStatus(Status status){
    	this.status=status;
    }
    public int getId(){
    	return id;
    }
    public void paste(){
    	this.status=Status.Paste;
    }
    public void noodle(){
    	this.status=Status.Noodle;
    }
    public void ripe(){
    	this.status=Status.Ripe;
    }
    public String toString(){
    	return id+" : "+status;
    }
}
class NoodlerQueue extends LinkedBlockingQueue<NoodleProcess>{
	private static final long serialVersionUID = 1L;};
	
class Paster implements Runnable{
	private NoodlerQueue pasteQueue;
	private int count=0;
	public Paster(NoodlerQueue queue){
		this.pasteQueue=queue;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(500);
				NoodleProcess noodler=new NoodleProcess(count++);
				noodler.setStatus(Status.Paste);
				System.out.println("pasting: "+noodler);
				pasteQueue.put(noodler);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
class Noodler implements Runnable{
	private NoodlerQueue pasteQueue;
	private NoodlerQueue noodleQueue;
	public Noodler(NoodlerQueue q1,NoodlerQueue q2){
		this.pasteQueue=q1;
		this.noodleQueue=q2;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				NoodleProcess noodle=pasteQueue.take();
				noodle.setStatus(Status.Noodle);
				System.out.println("noodling: "+noodle);
				noodleQueue.put(noodle);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
class Riper implements Runnable{
	private NoodlerQueue noodleQueue;
	private NoodlerQueue ripeQueue;
	public Riper(NoodlerQueue q1,NoodlerQueue q2){
		this.noodleQueue=q1;
		this.ripeQueue=q2;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				NoodleProcess noodle=noodleQueue.take();
				noodle.setStatus(Status.Ripe);
				System.out.println("riping: "+noodle);
				ripeQueue.put(noodle);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
}
class Eater implements Runnable{
	private NoodlerQueue ripeQueue;
	public Eater(NoodlerQueue q){
		this.ripeQueue=q;
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				NoodleProcess noodle=ripeQueue.take();
				if(noodle.getStatus()!=Status.Ripe){
					System.out.println("error-> "+noodle);
				}
				else{
					System.out.println("eating: "+noodle);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}









