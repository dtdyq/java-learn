package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2017/3/27.
 * java8������URLPermission�����࣬���ڹ���HttpURLConnection��Ȩ�����⣬�����
 * HttpURLConnection��װ�˰�ȫ��������ͨ���ö��������ʱ��Ҫ�Ȼ�ȡȨ��
 *
 * ����һ��URL���ӣ������������ȡURL���õ���Դ�Ĳ��裺
 *      1��ͨ��URL�����openConnection����URLConnection����
 *      2������URLConnection�Ĳ�������������
 *      3�����ֻ����GTE������ʹ��connect����������Զ����Դ�����Ӽ��ɣ�
 *         /���ʹ��POST������Ҫ��ȡURLConnection��������������������
 *      4��Զ����Դ��Ϊ���ã�������Է���ͷ�ֶκ�����
 *
 * �������Ҫʹ����������Ҫʹ�����������Ҫ��ʹ���������ʹ����������
 *
 * �������ʾ���������webվ�㷢��GET����POST����
 */
public class TestGetPost {
    public static void main(String[] args){

        String sget=TestGetPost.sendGET("http://localhost:8080/Test/",null);
        System.out.println(sget);

        String spost=TestGetPost.sendPost("https://mail.qq.com/cgi-bin/frame_html",
                "sid=evjJq&r=6ba2");
        System.out.println("\n\n\n"+spost);
    }
    /**
     * ����GET����
     * @param url
     * @param param
     * @return String
     */
    public static String sendGET(String url,String param){
        String result="";
        String urlName=url+"?"+param;
        try{
            URL realUrl=new URL(urlName);
            URLConnection conn=realUrl.openConnection();
            //����ͨ���������ԣ�
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            //�������ӣ�
            conn.connect();
            //��ȡ���е���Ӧͷ�ֶΣ�
            Map<String,List<String>> map=conn.getHeaderFields();
            //������Ӧͷ��Ϣ��
            for(String key:map.keySet()){
                System.out.println(key+"-->"+map.get(key));
            }

            try(
                    //��ȡURL��Ӧ��
                    BufferedReader in=new BufferedReader(
                            new InputStreamReader(conn.getInputStream(),"utf-8")
                    )
                    ){
                String line;
                while((line=in.readLine())!=null){
                    result+="\n"+line;
                }
            }catch(Exception e){
                System.out.println("get error");
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ����POST����
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url,String param){
        String result="";
        try{
            URL realurl=new URL(url);
            URLConnection conn=realurl.openConnection();
            //����ͨ���������ԣ�
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            //����POST�������ã�
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //��ȡOutputStream������POST����;
            PrintWriter pw=new PrintWriter(conn.getOutputStream());
            pw.print(param);
            pw.flush();

            //��ȡURL����Ӧ��
            BufferedReader br=new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8")
            );
            String line;
            while((line=br.readLine())!=null){
                result+="\n"+line;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
