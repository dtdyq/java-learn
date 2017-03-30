package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/3/25.
 * java7�ṩ��ForkJoinPool��֧�ֽ�һ�������ֳɶ��С������ExecutorService��ʵ����
 *
 * java8ΪForkJoinPool������ͨ�óع���
 *
 * ForkJoinTask������Բ��кϲ���������һ�������࣬�����������ࣺ
 *      RecursiveAction��û�з���ֵ������
 *      RecursiveTask���з���ֵ������
 */
public class TestForkJoinPool extends RecursiveAction{
    private static final int MAX_SIZE=50;
    private int beg;
    private int end;
    public TestForkJoinPool(int beg,int end){
        this.beg=beg;
        this.end=end;
    }
    @Override
    protected void compute() {
        if(end-beg>MAX_SIZE){
            int mid=(beg+end)/2;
            //(�첽��ʽ)����ִ����������
            new TestForkJoinPool(beg,mid).fork();
            new TestForkJoinPool(mid,end).fork();

        }
        else{
            for(int i=beg;i<end;i++){
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }
    public static void main(String[] args)throws Exception{
        ForkJoinPool pool=new ForkJoinPool();
        pool.submit(new TestForkJoinPool(1,100));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
