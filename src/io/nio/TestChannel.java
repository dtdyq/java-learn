package io.nio;
import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by dtdyq on 2017/3/8.
 *   Channel����ͨ����������������Ҫ������
 *      Channel����ֱ�ӽ�ָ�����ļ����ֻ�ȫ����ӳ��ΪBuffer
 *      ������ֱ�ӷ���Channel����ֻ����Buffer����
 *
 *   ʵ��Channel�����У�
 *      DatagramChannel��FileChannel��Pipe.SinkChannel
 *      Pipe.SourceChannel��SelectableChannel��SocketChannel
 *      ServerSocketChannel
 *
 *  ���е�Channel����Ӧ��ͨ�������������ʣ�����Ӧ��ͨ����ͳ�ڵ��
 *  ���������ʣ�
 *  Channel��õķ�����
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
            //��fin�е���������ӳ�䵽ByteBuffer�У�
            MappedByteBuffer buf=fin.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            //ʹ��ָ���ַ������н��룺
            Charset cset= Charset.forName("GBK");
            //��buf�е�����ȫ�������
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
    //����RandomAccessFile��getChannel������
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
