package thread;

/**
 * Created by Admin on 2017/3/24.
 * java使用ThreadGroup来表示线程组，可以对一批线程进行分类管理
 * 没有显式指定线程属于哪个线程组，则该线程属于默认线程组
 * 子线程和创建它的父线程默认在同一个线程组中
 * 一个线程加入某个线程组后，该线程会一直属于此线程组，直到该线程死亡
 *
 * ThreadGroup内定义了void uncaughtException(Thread t,Throwable e)
 *  该方法可以用来处理线程组内任意线程抛出的未处理
 *
 */
public class TestThreadGroup {
    public static void main(String[] args){
        ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();
        System.out.println("main thread's name : "+mainGroup);
        System.out.println("main threadGroup is a daemon thread group?: "
                    +mainGroup.isDaemon());
        new MyThread("mainThreadGroup's thread").start();

        ThreadGroup tg=new ThreadGroup("newThreadGroup");
        tg.setDaemon(true);
        System.out.println("tg threadGroup is a daemon thread group?: "
                +tg.isDaemon());
        MyThread mt=new MyThread(tg,"tg's Thread");
        mt.start();
    }
}
class MyThread extends Thread{
    public MyThread(String name){
        super(name);
    }
    public MyThread(ThreadGroup group,String name){
        super(group,name);
    }
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(getName()+"-->"+i);
        }
    }
}