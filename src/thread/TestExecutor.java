package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ϵͳ����һ���߳���Ҫ�ϸߵĳɱ�
 * java5��ʼjava�ڽ�֧���̳߳أ�������Executors�����������̳߳أ�
 * newCachedThreadPool(): ����һ�����л��湦�ܵ��̳߳�
 *
 * newFixedThreadPool(int nThreads): ���������õġ�����nThreads���̵߳��̳߳�
 *
 * newSingleThreadExecutor(): ����һ��ֻ�е��̵߳��̳߳�
 *
 * newScheduleThreadPool(int corePoolSize): ��������ָ���߳������̳߳أ�������
 * ��ָ���ӳٺ�ִ���߳�����
 *
 * newSingleThreadScheduleExecutor():����ֻ��һ���̵߳��̳߳�
 *
 * (java8)ExecutorService new WorkStealingPool(int parallelism):��������
 * �㹻���̵߳��̳߳���֧�ָ����Ĳ��м��𣬸÷�����ʹ�ö�����������پ���
 *
 * (java8)ExecutorService new WorkStealingPool():��һ�������ļ򻯰棬�����ǰ������
 * 4��CPU����Ŀ�겢�м�������Ϊ4
 *
 *
 * ExecutorService������ִ���̵߳��̳߳أ��ṩ������������
 * 		Future<?> submit(Runnable task):Runnableû�з���ֵ�����з���null������
 * 			���Ե���Future��isDone()��isCancelled()���������Runnableִ��״̬
 * 	    <T> Future<T> submit(Runnable task,T result):resultΪ�߳�ִ����ϵ�
 * 	    	����ֵ
 * 	   	<T> Future<T> submit(callable<T> task):Future����call�����ķ���ֵ
 * ScheduleExecutorService ���������ָ���ӳٺ�������Ե�ִ���߳�������̳߳�
 *
 * ����һ���̳߳غ����shutDown���������Բ��ٽ����µ��߳�����
 * ����shutDownNow������������ֹͣ���������ִ��
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
