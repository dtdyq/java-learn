package jvm.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author dtdyq
 * 
 * jstack用于生成虚拟机当前时刻的线程快照，一般称为threaddump和javacore文件，生成线程
 * 快照的主要目的是定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致
 * 长时间等待
 * 
 * 命令格式：
 *     -F：当正常输出的请求不被响应时，强制输出线程堆栈
 *     -l：除堆栈外，显示关于锁的附加信息
 *     -m：如果调用到本地方法的话，可以显示c/c++的堆栈
 *
 */
public class Tools_Jstack {
	synchronized static void M1(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		M2();
	}
	synchronized static void M2(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		M1();
	}
	public static void main(String[] args){
        Thread t1 = new Thread(()->{
        	M1();
        });	
        Thread t2 = new Thread(()->{
        	M2();
        });	
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(t1);
        exec.execute(t2);
        exec.shutdown();
	}
}










