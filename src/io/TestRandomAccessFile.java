package io;
import java.io.*;

/**
 * Created by dtdyq on 2017/3/7.
 *  RandomAccessFile :
 *      功能丰富的文件访问类，可以随机地在文件的任意位置进行读取写入操作
 *          long getFilePointer()--->返回文件指针的当前位置
 *          void seek(long pointer)-->将文件指针定位到指定位置
 *      构造RandomAccessFile对象时，除了指定文件路径外，还有文件操作模式：
 *          "r":以只读的形式打开文件
 *          "rw"：以读写的方式打开文件
 *          "rws":要求对文件的内容或元数据的每个更新都同步写入底层存储设备
 *          "rwd":对文件内容的每个更新都同步写入底层存储设备
 */
public class TestRandomAccessFile {
    public static void InitTest(){
        try {
            RandomAccessFile ras = new RandomAccessFile("file/io/FileTest.txt", "rw");
            ras.seek(100);
            byte[] buff=new byte[1024];
            int len=-1;
            while((len=ras.read(buff))!=-1){
                System.out.println(new String(buff,0,len));
            }
            ras.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void appendTest(){
        try{
            RandomAccessFile ras=new RandomAccessFile("file/io/FileTest.txt","rw");
            long length=ras.length();
            ras.seek(length);
            ras.write("\n94735834".getBytes());
            ras.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void insertTest(){
        try{
            RandomAccessFile ras=new RandomAccessFile("file/io/FileTest.txt","rw");
            ras.seek(500);
            ras.write("insert test".getBytes());
            ras.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        TestRandomAccessFile.insertTest();
        TestRandomAccessFile.appendTest();
        TestRandomAccessFile.InitTest();
    }
}
