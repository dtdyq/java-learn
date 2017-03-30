package io;
import java.io.*;

/**
 * Created by dtdyq on 2017/3/7.
 *  RandomAccessFile :
 *      ���ܷḻ���ļ������࣬������������ļ�������λ�ý��ж�ȡд�����
 *          long getFilePointer()--->�����ļ�ָ��ĵ�ǰλ��
 *          void seek(long pointer)-->���ļ�ָ�붨λ��ָ��λ��
 *      ����RandomAccessFile����ʱ������ָ���ļ�·���⣬�����ļ�����ģʽ��
 *          "r":��ֻ������ʽ���ļ�
 *          "rw"���Զ�д�ķ�ʽ���ļ�
 *          "rws":Ҫ����ļ������ݻ�Ԫ���ݵ�ÿ�����¶�ͬ��д��ײ�洢�豸
 *          "rwd":���ļ����ݵ�ÿ�����¶�ͬ��д��ײ�洢�豸
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
