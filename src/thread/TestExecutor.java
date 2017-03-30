package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 系统启动一个线程需要较高的成本
 * java5后开始java内建支持线程池，新增了Executors工厂来产生线程池：
 * newCachedThreadPool(): 创建一个具有缓存功能的线程池
 *
 * newFixedThreadPool(int nThreads): 创建可重用的、具有nThreads个线程的线程池
 *
 * newSingleThreadExecutor(): 创建一个只有单线程的线程池
 *
 * newScheduleThreadPool(int corePoolSize): 创建具有指定线程数的线程池，它可以
 * 在指定延迟后执行线程任务
 *
 * newSingleThreadScheduleExecutor():创建只有一个线程的线程池
 *
 * (java8)ExecutorService new WorkStealingPool(int parallelism):创建持有
 * 足够的线程的线程池来支持给定的并行级别，该方法会使用多个队列来减少竞争
 *
 * (java8)ExecutorService new WorkStealingPool():上一个方法的简化版，如果当前机器有
 * 4个CPU，则目标并行级别被设置为4
 *
 *
 * ExecutorService代表尽快执行线程的线程池，提供了三个方法：
 * 		Future<?> submit(Runnable task):Runnable没有返回值，所有返回null，不过
 * 			可以调用Future的isDone()、isCancelled()方法来获得Runnable执行状态
 * 	    <T> Future<T> submit(Runnable task,T result):result为线程执行完毕的
 * 	    	返回值
 * 	   	<T> Future<T> submit(callable<T> task):Future代表call方法的返回值
 * ScheduleExecutorService 代表可以在指定延迟后或周期性地执行线程任务的线程池
 *
 * 用完一个线程池后调用shutDown方法，可以不再接受新的线程任务
 * 调用shutDownNow方法，会立即停止所有任务的执行
 */
public class TestExecutor{
	public static void main(String[] args){
		ExecutorService pool= Executors.newFixedThreadPool(2);
		pool.submit(new myRun());
		pool.submit(new myRun());
		pool.shutdown();
	}
}
class myRun implements Runnable{
	public void run(){
		for(int x=0;x<100;x++){
			System.out.println(Thread.currentThread().getName()+":"+x);

		}
	}
}
