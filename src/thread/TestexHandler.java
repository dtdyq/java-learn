package thread;

/**
 * Created by dtdyq on 2017/3/24.
 * java5开始，java加强了线程的异常处理，如果线程执行过程中抛出未处理异常
 * JVM在结束该线程之前，会检查是否有对应的Thread.UncaughtExceptionHandler
 * 对象，如果有的话，则会调用该对象的uncanghtException(Thread t,throwable e)
 * 方法来处理该异常
 *
 * Thread.UncanghtExceptionHandler是Thread类的一个静态内部接口，该接口只有一个
 * uncaughtException方法，其中t是出现异常的线程，e是代表该线程抛出的异常
 *
 * Thread提供了：
 *      setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler e)
 *              为该线程类所有线程实例设置默认的异常处理处理器
 *      setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler e)
 *              为指定线程实例设置异常处理器
 */
public class TestexHandler {
    @SuppressWarnings("unused")
	public static void main(String[] args){
        Thread.currentThread().setUncaughtExceptionHandler(new myExHandler());
        int a=12/0;
        System.out.println("program exit with 0");
    }

}
class myExHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t+" thread has exception :"+e);
    }
}