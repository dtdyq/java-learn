package io.nio;

import java.nio.CharBuffer;

/**
 * Created by dtdyq on 2017/3/8.
 *  Buffer类似于数组，用于保存多个同类型的数据：
 *      有CharBuffer、ShortBuffer、IntBuffer、LongBuffer、ByteBuffer等
 *      最常用的事ByteBuffer和CharBuffer
 *
 *      ByteBuffer有一个子类：MappedByteBuffer 表示Channel将磁盘文件的部分或者全部映射到内存
 *      中得到的结果
 *
 *      Buffer的三个概念：
 *          capacity：缓冲区的容量，表示该Buffer的最大容量
 *          limit：   第一个不应该被读出或写入的缓冲区位置索引
 *          position：用于指明下一个可以被独处或写入的的缓冲区位置的索引
 *
 */
public class TestBuffer {
    public static void test(){
        //初始化缓冲区大小：
        CharBuffer cb=CharBuffer.allocate(8);
        System.out.println("capacity-->"+cb.capacity());
        System.out.println("limit-->"+cb.limit());
        System.out.println("position-->"+cb.position());

        cb.put('a');
        cb.put('b');
        cb.put('c');
        System.out.println("capacity-->"+cb.capacity());
        System.out.println("limit-->"+cb.limit());
        System.out.println("position-->"+cb.position());
        //将position置于缓冲区开始处，limit置于缓冲数据末尾
        cb.flip();
        System.out.println("limit-->"+cb.limit());
        System.out.println("position-->"+cb.position());
        //不删除数据，将position置0，limit置为capacity：
        cb.clear();
        System.out.println("limit-->"+cb.limit());
        System.out.println("position-->"+cb.position());
        System.out.println("data-->"+cb.get());
        System.out.println("position-->"+cb.position());
        System.out.println("data-->"+cb.get());
        System.out.println("data-->"+cb.get());
        //没有数据，返回空：
        System.out.println("data-->"+cb.get());
    }
    public static void main(String[] args){
        test();
    }
}
