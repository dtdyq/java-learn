package io.nio;
import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by dtdyq on 2017/3/8.
 *   Channel和普通的流对象有两个主要的区别：
 *      Channel可以直接将指定的文件部分或全部的映射为Buffer
 *      程序不能直接访问Channel对象，只能与Buffer交互
 *
 *   实现Channel的类有：
 *      DatagramChannel、FileChannel、Pipe.SinkChannel
 *      Pipe.SourceChannel、SelectableChannel、SocketChannel
 *      ServerSocketChannel
 *
 *  所有的Channel都不应该通过构造器来访问，而是应该通过传统节点的
 *  方法来访问：
 *  Channel最常用的方法：
 *      map()
 *      read()
 *      write()
 */
public class TestChannel {
    public static void test(){
        File f=new File("file/io/FileTest.txt");
        try{
            FileChannel fin=new FileInputStream(f).getChannel();
            FileChannel fout=new FileOutputStream("file/io/Channel.txt").getChannel();
            //将fin中的所有数据映射到ByteBuffer中：
            MappedByteBuffer buf=fin.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            //使用指定字符集进行解码：
            Charset cset= Charset.forName("GBK");
            //将buf中的数据全部输出：
            fout.write(buf);
            buf.clear();
            CharsetDecoder cd=cset.newDecoder();
            CharBuffer cbuf=cd.decode(buf);
            System.out.println(cbuf);

            fin.close();
            fout.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //测试RandomAccessFile的getChannel方法：
    public static void testrsf(){
        try{
            RandomAccessFile raf=new RandomAccessFile(new File("file/io/FileTest.txt"),"rw");
            FileChannel fc=raf.getChannel();
            ByteBuffer buff=fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size());
            fc.position(fc.size());
            fc.write(buff);
            raf.close();
            fc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        testrsf();
        test();

    }
}
