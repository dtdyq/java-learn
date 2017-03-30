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
    //打印出所有系统支持的字符集：
    public static void charName(){
        SortedMap<String,Charset> map=Charset.availableCharsets();
        for(String str:map.keySet()){
            System.out.println(str+"-->"+map.get(str));
        }
    }
    public static void test(){
        //创建Charset对象：
        Charset cset=Charset.forName("GBK");
        //获取编码或解码对象：
        CharsetEncoder ce=cset.newEncoder();
        CharsetDecoder cd=cset.newDecoder();
        /*
        用decode或encode方法对指定数组进行编码或解码
         */
    }
    public static void main(String[] args){
        charName();
    }
}
