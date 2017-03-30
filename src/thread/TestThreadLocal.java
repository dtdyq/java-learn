package thread;

/**
 * Created by Admin on 2017/3/25.
 * ThreadLocal类：
 *      为每一个使用该变量的线程都提供一个该变量值的副本，使每一个线程都可以独立的改变
 *      自己的副本
 */
public class TestThreadLocal {
    public static void main(String[] args){
        Tests test=new Tests("init");
        new MyTest(test,"A").start();
        new MyTest(test,"B").start();
        System.out.println("main thread: "+test.getName());
    }
}
class Tests{
    //定义一个ThreadLocal类型的变量，该变量将是一个线程局部变量，每个线程都会保留
    //该变量的一个副本
    private ThreadLocal<String> name=new ThreadLocal<>();
    public Tests(String name){
        this.name.set(name);
    }
    public String getName(){
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }
}
class MyTest extends Thread{
    private Tests test;
    public MyTest(Tests test,String name){
        super(name);
        this.test=test;
    }
    public void run(){
        for(int i=0;i<10;i++) {
            if (i == 6) {
                test.setName(getName());
            }
            System.out.println(test.getName() + " test's i: " + i);
        }
    }
}