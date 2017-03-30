package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/3/25.
 * java7提供了ForkJoinPool来支持将一个任务拆分成多个小任务，是ExecutorService的实现类
 *
 * java8为ForkJoinPool增加了通用池功能
 *
 * ForkJoinTask代表可以并行合并的任务，是一个抽象类，两个抽象子类：
 *      RecursiveAction：没有返回值的任务
 *      RecursiveTask：有返回值的任务
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
            //(异步方式)并行执行两个任务：
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
