package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2017/3/24.
 * java5��ʼ��java�ṩ�˸�ǿ����߳�ͬ������---ͨ����ʾ����ͬ����������ʵ��ͬ��
 * Lock�ǿ��ƶ���̶߳Թ�����Դ���з��ʵĹ���
 * Lock--->ReentrantLock
 * ReadWriteLock--->ReentrantLock
 *
 * java8������StampedLock�࣬Ϊ��д�ṩ��������ģʽ��Writing��ReadingOptimistic
 * ��Reading
 *
 */
public class TestLock {
    //����������
    private final ReentrantLock lock=new ReentrantLock();

    private String accountNo;
    private double balance;
    public TestLock(){};
    public TestLock(String accountNo,double balance){
        this.accountNo=accountNo;
        this.balance=balance;
    }
    public void test(double amount){
        lock.lock();
        try{
            if(amount <=balance){
                System.out.println(Thread.currentThread().getName()+
                        "draw success ! "+amount);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                balance-=amount;
                System.out.println("balance now :"+balance);
            }else{
                System.out.println(Thread.currentThread().getName()
                        +"  draw error");
            }
        }finally{
            lock.unlock();
        }
    }
}
class AccountThread extends Thread{
    private TestLock account;
    private double amount;
    public AccountThread(String name,TestLock account,double amount){
        super(name);
        this.account=account;
        this.amount=amount;
    }
    public void run(){
        account.test(amount);
    }
    public static void main(String[] args){
        TestLock tl=new TestLock("7863",654.65);
        new AccountThread("��",tl,546).start();
        new AccountThread("��",tl,546).start();
    }
}