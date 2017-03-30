package thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Admin on 2017/3/24.
 * java5�ṩ��BlockingQueue�ӿڣ���Queue���ӽӿڣ���Ҫ������Ϊ�߳�ͬ����
 * ����
 * ���������������߳���ͼ��BlockingQueue�з���Ԫ�أ�����ö������������߳�
 * ������
 *      ���������߳���ͼ��BlockingQueue��ȡ��Ԫ��ʱ������ö����ѿգ����߳�
 * ������
 *
 * put(E e):���԰�Ԫ��e����BlockingQueue�У�����ö��е�Ԫ��������������
 * take()  :���Դ�BlockingQueueͷ��ȡ��Ԫ�أ��������Ϊ�գ����߳�����
 *
 * BlockingQueue��ʵ���ࣺ
 *  ArrayBlockingQueue
 *  LinkedBlockingQueue
 *  PriorityBlockingQueue
 *  SynchronousQueue
 *  DelayQueue
 */
public class TestBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(4);
        new Produ(queue).start();
        new Consu(queue).start();
        new Consu(queue).start();
    }
}
class Produ extends Thread{
    private ArrayBlockingQueue<String> queue;
    public Produ(ArrayBlockingQueue<String> queue){
        this.queue=queue;
    }
    public void run() {
        String[] arr = {"java", "c++", "python", "lisp"};
        int i=0;
        while(true){

            try {
                Thread.sleep(200);
                queue.put(arr[i++ % 4]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " is producing "+queue);
        }
    }
}
class Consu extends Thread{
    private ArrayBlockingQueue<String> queue;
    public Consu(ArrayBlockingQueue<String> queue){
        this.queue=queue;
    }
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                queue.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " is consuming " + queue);
        }
    }
}