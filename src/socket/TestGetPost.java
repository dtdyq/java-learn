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
 * java8新增了URLPermission工具类，用于管理HttpURLConnection的权限问题，如果在
 * HttpURLConnection安装了安全管理器，通过该对象打开连接时需要先获取权限
 *
 * 创建一个URL连接，并发送请求读取URL引用的资源的步骤：
 *      1、通过URL对象的openConnection创建URLConnection对象
 *      2、设置URLConnection的参数和请求属性
 *      3、如果只发送GTE请求，则使用connect方法建立和远程资源的连接即可，
 *         /如果使用POST，则需要获取URLConnection的输出流来发送请求参数
 *      4、远程资源变为可用，程序可以访问头字段和数据
 *
 * （如果既要使用输入流又要使用输出流，则要先使用输出流再使用输入流）
 *
 * 下面程序示范了如何向web站点发送GET请求、POST请求
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
     * 发送GET请求：
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
            //设置通用请求属性：
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            //建立连接：
            conn.connect();
            //获取所有的响应头字段：
            Map<String,List<String>> map=conn.getHeaderFields();
            //遍历响应头信息：
            for(String key:map.keySet()){
                System.out.println(key+"-->"+map.get(key));
            }

            try(
                    //读取URL响应：
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
     * 发送POST请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url,String param){
        String result="";
        try{
            URL realurl=new URL(url);
            URLConnection conn=realurl.openConnection();
            //设置通用请求属性：
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            //发送POST请求设置：
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取OutputStream并发送POST请求;
            PrintWriter pw=new PrintWriter(conn.getOutputStream());
            pw.print(param);
            pw.flush();

            //读取URL的响应：
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
