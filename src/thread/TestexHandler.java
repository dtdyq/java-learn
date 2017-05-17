package thread;

/**
 * Created by dtdyq on 2017/3/24.
 * java5��ʼ��java��ǿ���̵߳��쳣��������߳�ִ�й������׳�δ�����쳣
 * JVM�ڽ������߳�֮ǰ�������Ƿ��ж�Ӧ��Thread.UncaughtExceptionHandler
 * ��������еĻ��������øö����uncanghtException(Thread t,throwable e)
 * ������������쳣
 *
 * Thread.UncanghtExceptionHandler��Thread���һ����̬�ڲ��ӿڣ��ýӿ�ֻ��һ��
 * uncaughtException����������t�ǳ����쳣���̣߳�e�Ǵ�����߳��׳����쳣
 *
 * Thread�ṩ�ˣ�
 *      setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler e)
 *              Ϊ���߳��������߳�ʵ������Ĭ�ϵ��쳣��������
 *      setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler e)
 *              Ϊָ���߳�ʵ�������쳣������
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