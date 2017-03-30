package thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Admin on 2017/3/24.
 * java5提供了BlockingQueue接口，是Queue的子接口，主要用来作为线程同步的
 * 工具
 * 特征：当生产者线程试图向BlockingQueue中放入元素，如果该队列已满，则线程
 * 被阻塞
 *      当消费者线程试图从BlockingQueue中取出元素时，如果该队列已空，则线程
 * 被阻塞
 *
 * put(E e):尝试把元素e放入BlockingQueue中，如果该队列的元素已满，则阻塞
 * take()  :尝试从BlockingQueue头部取出元素，如果队列为空，则线程阻塞
 *
 * BlockingQueue的实现类：
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