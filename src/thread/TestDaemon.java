package thread;

import junit.framework.Test;

/**
 * Created by Admin on 2017/3/23.
 * �ں�̨���У�������Ϊ�������߳��ṩ������̳߳�Ϊ�ػ��߳�
 * �ص㣺�������ǰ̨�̶߳���������̨�̻߳��Զ�����
 * ����Thread��setDaemon(true)���������Խ�ָ���߳����óɺ�̨�߳�
 *
 * ǰ̨�̴߳��������߳�Ĭ��Ϊǰ̨�̣߳���̨�̴߳��������߳�Ĭ��Ϊ��̨�߳�
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
