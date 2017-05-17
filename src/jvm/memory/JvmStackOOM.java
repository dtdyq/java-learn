package jvm.memory;

/**
 * @author dtdyq
 * java虚拟机栈出现OutOfMemoryError异常：
 * 只有在多线程的情况下才能出现OOM异常
 * 因为计算机所能分配的内存有限，减去方法区和堆内存，剩下的即是虚拟机栈所能使用的
 * 内存大小，当线程过多时，内存很快不足从而出现OOM异常
 * 
 * 注意：参数-Xss是为每个线程分配的内存大小，当出现虚拟机栈的OOM异常时，如果线程数
 * 不能减少，就应该减小该参数值，使得每个线程占有的栈内存更小，从而容纳更多的线程
 * 
 * -Xss2m
 */
public class JvmStackOOM{
	void dontStop(){
		while(true){
			
		}
	}
	public static void main(String[] args){
		while(true){
			new Thread(()->{
			    new JvmStackOOM().dontStop();
			}
		    ).start();
		}
	}
	
}
