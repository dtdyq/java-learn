package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2017/3/24.
 * �������ʹ��synchronized�ؼ�������֤ͬ��������ֱ��ʹ��Lock��������֤ͬ��
 * ��ϵͳ�в�������ʽ��ͬ����������
 *
 * ʹ��Lock��������֤ͬ��ʱ��java�ṩ��Condition�ౣ��Э����ʹ��Condition����
 * ����Щ�Ѿ��Ѿ��õ�Lock����ȴ�޷�����ִ�е��߳��ͷ�Lock����Condition����
 * Ҳ���Ի����������ڵȴ����߳�
 *
 */
public class TestCondition {
    private int product;
    private final Lock lock=new ReentrantLock();
    private final Condition cond=lock.newCondition();
    public void consume(){
        lock.lock();
        if(product==0){
            try {
                cond.await();
                cond.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("consume: "+(product--));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cond.signalAll();
        }
        lock.unlock();
    }
    public void produce() {
        lock.lock();
        if(product>=100){
            try {
                cond.await();
                cond.signalAll();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("produce: "+(product+=5));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cond.signalAll();
        lock.unlock();
    }
    public static void main(String[] args){
        TestCondition tc=new TestCondition();
        new Thread(new Consumes(tc),"frist").start();
        new Thread(new Consumes(tc),"second").start();
        new Thread(new Consumes(tc),"third").start();
        new Thread(new Productor(tc),"product").start();
    }
}
class Consumes implements Runnable{
    private TestCondition cond;
    public Consumes(TestCondition cond){
        this.cond=cond;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            cond.consume();
        }
    }
}
class Productor implements Runnable{
    private TestCondition cond;
    public Productor(TestCondition cond){
        this.cond=cond;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            cond.produce();
        }
    }
}