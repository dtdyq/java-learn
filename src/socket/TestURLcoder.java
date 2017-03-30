package socket;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Admin on 2017/3/26.
 * URLDecoder��URLEncoder���������ͨ�ַ�����
 * application/x-www-form-urlencoded MIME�ַ���֮���ת������һ���ַ�����
 * ��ʹ�÷���ŷ�ַ����ַ�����URL��ת���ɵ��ַ���
 */
public class TestURLcoder {
    public static void main(String[] args){
        try {
            //��URL�еķ���ŷ�ַ����뻹ԭ��ԭ�����ַ�����
            // �ڶ���������ʾʹ��utf-8����
            String word= URLDecoder.decode("%E6%88%91%E7%88%B1java","utf-8");
            System.out.println(word);
            //����ͨ�ַ���ת��Ϊapplication/x-www-form-urlencoded MIME
            String key= URLEncoder.encode("�Ұ�java","utf-8");
            System.out.println(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}














