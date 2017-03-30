package io.nio;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * Created by dtdyq on 2017/3/8.
 *
 */
public class TestCharset {
    //��ӡ������ϵͳ֧�ֵ��ַ�����
    public static void charName(){
        SortedMap<String,Charset> map=Charset.availableCharsets();
        for(String str:map.keySet()){
            System.out.println(str+"-->"+map.get(str));
        }
    }
    public static void test(){
        //����Charset����
        Charset cset=Charset.forName("GBK");
        //��ȡ�����������
        CharsetEncoder ce=cset.newEncoder();
        CharsetDecoder cd=cset.newDecoder();
        /*
        ��decode��encode������ָ��������б�������
         */
    }
    public static void main(String[] args){
        charName();
    }
}
