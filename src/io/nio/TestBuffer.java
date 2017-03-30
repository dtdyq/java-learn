package io.nio;

import java.nio.CharBuffer;

/**
 * Created by dtdyq on 2017/3/8.
 *  Buffer���������飬���ڱ�����ͬ���͵����ݣ�
 *      ��CharBuffer��ShortBuffer��IntBuffer��LongBuffer��ByteBuffer��
 *      ��õ���ByteBuffer��CharBuffer
 *
 *      ByteBuffer��һ�����ࣺMappedByteBuffer ��ʾChannel�������ļ��Ĳ��ֻ���ȫ��ӳ�䵽�ڴ�
 *      �еõ��Ľ��
 *
 *      Buffer���������
 *          capacity������������������ʾ��Buffer���������
 *          limit��   ��һ����Ӧ�ñ�������д��Ļ�����λ������
 *          position������ָ����һ�����Ա�������д��ĵĻ�����λ�õ�����
 *
 */
public class TestBuffer {
    public static void test(){
        //��ʼ����������С��
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
        //��position���ڻ�������ʼ����limit���ڻ�������ĩβ
        cb.flip();
        System.out.println("limit-->"+cb.limit());
        System.out.println("position-->"+cb.position());
        //��ɾ�����ݣ���position��0��limit��Ϊcapacity��
        cb.clear();
        System.out.println("limit-->"+cb.limit());
        System.out.println("position-->"+cb.position());
        System.out.println("data-->"+cb.get());
        System.out.println("position-->"+cb.position());
        System.out.println("data-->"+cb.get());
        System.out.println("data-->"+cb.get());
        //û�����ݣ����ؿգ�
        System.out.println("data-->"+cb.get());
    }
    public static void main(String[] args){
        test();
    }
}
