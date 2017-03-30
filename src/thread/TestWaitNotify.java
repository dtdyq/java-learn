package thread;

/**
 * Created by Admin on 2017/3/24.
 * �߳̿���ͨ��wait/notify/notifyAll�������ȴ����������߳�
 */
public class TestWaitNotify {
    public static void main(String[] args){
        Bank b=new Bank(500);
        new DrawThread("a",b,300).start();
        new DrawThread("b",b,200).start();
        new DrawThread("c",b,100).start();
        new DepositThread("x",b,500).start();

    }
}
class Bank{
    private int balance;
    private boolean flag=false;
    public Bank(int balance){
        this.balance=balance;
    }
    //ȡǮ��
    public synchronized void draw(int count){
        try{
            if(!flag){
                wait();
            }
            else{
                System.out.println(Thread.currentThread().getName()
                            +" get money "+count);
                balance-=count;
                System.out.println("now balance: "+balance);
                flag=false;
                notifyAll();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //��Ǯ��
    public synchronized void deposit(int num){
        try{
            if(flag){
                wait();
            }
            else {
                System.out.println(Thread.currentThread().getName() +
                        " put money " + num);
                balance += num;
                System.out.println("now balance: " + balance);
                flag=true;
                notifyAll();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
//ȡǮ�ߣ�
class DrawThread extends Thread{
    Bank b;
    int account;
    public DrawThread(String name,Bank b,int account){
        super(name);
        this.b=b;
        this.account=account;
    }
    public void run(){
        for(int i=0;i<10;i++){
            b.draw(account);
        }
    }
}
//��Ǯ�ߣ�
class DepositThread extends Thread{
    Bank b;
    int account;
    public DepositThread(String name,Bank b,int account){
        super(name);
        this.b=b;
        this.account=account;
    }
    public void run(){
        for(int i=0;i<10;i++){
            b.deposit(account);
        }
    }
}
