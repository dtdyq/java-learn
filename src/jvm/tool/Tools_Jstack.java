package jvm.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author dtdyq
 * 
 * jstack���������������ǰʱ�̵��߳̿��գ�һ���Ϊthreaddump��javacore�ļ��������߳�
 * ���յ���ҪĿ���Ƕ�λ�̳߳��ֳ�ʱ��ͣ�ٵ�ԭ�����̼߳���������ѭ���������ⲿ��Դ����
 * ��ʱ��ȴ�
 * 
 * �����ʽ��
 *     -F����������������󲻱���Ӧʱ��ǿ������̶߳�ջ
 *     -l������ջ�⣬��ʾ�������ĸ�����Ϣ
 *     -m��������õ����ط����Ļ���������ʾc/c++�Ķ�ջ
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










