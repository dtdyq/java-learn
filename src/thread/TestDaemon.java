package thread;

import junit.framework.Test;

/**
 * Created by Admin on 2017/3/23.
 * 在后台运行，任务是为其他的线程提供服务的线程成为守护线程
 * 特点：如果所有前台线程都死亡，后台线程会自动死亡
 * 调用Thread的setDaemon(true)方法，可以将指定线程设置成后台线程
 *
 * 前台线程创建的子线程默认为前台线程，后台线程创建的子线程默认为后台线程
 */
public class TestDaemon extends Thread{
    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println(this.getName()+"-->"+i);
        }
    }
    public static void main(String[] args){
        TestDaemon td=new TestDaemon();
        td.setDaemon(true);
        td.start();
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }
}
