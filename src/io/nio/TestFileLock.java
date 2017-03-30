package io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by dtdyq on 2017/3/8.
 *  �ļ����������ļ����������������벢��ϵͳ�ж�����̶��ļ����в��������
 */
public class TestFileLock {
    public static void test(){
        try{
            FileChannel fchannel=new FileOutputStream("file/io/Channel.txt").getChannel();
            try {
                FileLock flock=fchannel.tryLock();
                Thread.sleep(1000);
                flock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        test();
    }
}
