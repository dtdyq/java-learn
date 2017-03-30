package socket;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Admin on 2017/3/26.
 * java使用InetAddress来代表IPP地址，Inet4Address、Inet6Address是它的两个
 * 子类。InetAddress类提供了两个静态方法来获取InetAddress实例：
 *      getByName(String host) : 根据主机获取InetAddress对象
 *      getByAddress(byte[] addr): 根据原始IP地址获取对应的InetAddress对象
 *    提供了三个方法来获取InetAddress对应的IP地址和主机名
 *      getCanonicalHostName(): 获取该IP地址对应的全限定域名
 *      getHostAddress(): 返回对应的Ip字符串
 *      getHostName(): 获取IP地址的主机名
 *
 *    IInetAddress提供了getLocalHost方法来获取本机IP地址对应的InetAddress实例
 */
public class TestInetAddress {
    public static void main(String[] args){
        try {
            InetAddress ip=InetAddress.getByName("www.baidu.com");
            //判断是否可达：
            System.out.println(ip.isReachable(10));
            //获取Ip地址：
            System.out.println(ip.getHostAddress());
            //获取主机名：
            System.out.println(ip.getHostName());
            //获取全限定域名：
            System.out.println(ip.getCanonicalHostName());
            //使用IP地址获取InetAddress实例：
            InetAddress lip=InetAddress.getByAddress(new byte[]{127,0,0,1});
            //判断本机地址是否可达：
            System.out.println(lip.isReachable(10));
            //使用默认方法来获取本机InetAddress实例：
            InetAddress llip=InetAddress.getLocalHost();
            System.out.println(llip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










