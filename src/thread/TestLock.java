package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2017/3/24.
 * java5开始，java提供了更强大的线程同步机制---通过显示定义同步锁对象来实现同步
 * Lock是控制多个线程对共享资源进行访问的工具
 * Lock--->ReentrantLock
 * ReadWriteLock--->ReentrantLock
 *
 * java8新增了StampedLock类，为读写提供了三种锁模式：Writing、ReadingOptimistic
 * 、Reading
 *
 */
public class TestLock {
    //定义锁对象
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
        new AccountThread("甲",tl,546).start();
        new AccountThread("乙",tl,546).start();
    }
}