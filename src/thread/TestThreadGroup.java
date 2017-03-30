package thread;

/**
 * Created by Admin on 2017/3/24.
 * javaʹ��ThreadGroup����ʾ�߳��飬���Զ�һ���߳̽��з������
 * û����ʽָ���߳������ĸ��߳��飬����߳�����Ĭ���߳���
 * ���̺߳ʹ������ĸ��߳�Ĭ����ͬһ���߳�����
 * һ���̼߳���ĳ���߳���󣬸��̻߳�һֱ���ڴ��߳��飬ֱ�����߳�����
 *
 * ThreadGroup�ڶ�����void uncaughtException(Thread t,Throwable e)
 *  �÷����������������߳����������߳��׳���δ����
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