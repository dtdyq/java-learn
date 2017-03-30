package socket;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Admin on 2017/3/26.
 * URLDecoder和URLEncoder用于完成普通字符串和
 * application/x-www-form-urlencoded MIME字符串之间的转换，后一个字符串是
 * 在使用非西欧字符的字符串是URL中转化成的字符串
 */
public class TestURLcoder {
    public static void main(String[] args){
        try {
            //将URL中的非西欧字符编码还原成原来的字符串，
            // 第二个参数表示使用utf-8解码
            String word= URLDecoder.decode("%E6%88%91%E7%88%B1java","utf-8");
            System.out.println(word);
            //将普通字符串转化为application/x-www-form-urlencoded MIME
            String key= URLEncoder.encode("我爱java","utf-8");
            System.out.println(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}














