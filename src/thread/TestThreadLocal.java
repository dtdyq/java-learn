package thread;

/**
 * Created by Admin on 2017/3/25.
 * ThreadLocal�ࣺ
 *      Ϊÿһ��ʹ�øñ������̶߳��ṩһ���ñ���ֵ�ĸ�����ʹÿһ���̶߳����Զ����ĸı�
 *      �Լ��ĸ���
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
    //����һ��ThreadLocal���͵ı������ñ�������һ���ֲ߳̾�������ÿ���̶߳��ᱣ��
    //�ñ�����һ������
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