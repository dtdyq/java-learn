package thread;

/**
 * Created by Admin on 2017/3/24.
 * 当两个线程同时等待对方释放同步监视器时就会发生死锁
 */
public class TestDeadlock extends Thread{
    A a=new A();
    B b=new B();
    public void init(){
        Thread.currentThread().setName("main thread");
        a.foo(b);
        System.out.println("after the main thread");
    }
    public void run(){
        Thread.currentThread().setName("other thread");
        b.bar(a);
        System.out.println("after the other thread");
    }
    public static void main(String[] args){
        TestDeadlock tdl=new TestDeadlock();
        tdl.start();
        tdl.init();
    }
}
class A{
    public synchronized void foo(B b){
        System.out.println("thread name : "+Thread.currentThread().getName()
                    +" into the method of A");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+
                " try to invoke B's last method");
        b.last();
    }
    public synchronized void last(){
        System.out.println("into A's last method");
    }
}
class B{
    public synchronized void bar(A a){
        System.out.println("Thread name : "+Thread.currentThread().getName()
                    +" into the Method of B");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+
                        " try to invoke A's last method");
        a.last();
    }
    public synchronized void last(){
        System.out.println("into B's last method");
    }
}
