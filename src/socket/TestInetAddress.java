package socket;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Admin on 2017/3/26.
 * javaʹ��InetAddress������IPP��ַ��Inet4Address��Inet6Address����������
 * ���ࡣInetAddress���ṩ��������̬��������ȡInetAddressʵ����
 *      getByName(String host) : ����������ȡInetAddress����
 *      getByAddress(byte[] addr): ����ԭʼIP��ַ��ȡ��Ӧ��InetAddress����
 *    �ṩ��������������ȡInetAddress��Ӧ��IP��ַ��������
 *      getCanonicalHostName(): ��ȡ��IP��ַ��Ӧ��ȫ�޶�����
 *      getHostAddress(): ���ض�Ӧ��Ip�ַ���
 *      getHostName(): ��ȡIP��ַ��������
 *
 *    IInetAddress�ṩ��getLocalHost��������ȡ����IP��ַ��Ӧ��InetAddressʵ��
 */
public class TestInetAddress {
    public static void main(String[] args){
        try {
            InetAddress ip=InetAddress.getByName("www.baidu.com");
            //�ж��Ƿ�ɴ
            System.out.println(ip.isReachable(10));
            //��ȡIp��ַ��
            System.out.println(ip.getHostAddress());
            //��ȡ��������
            System.out.println(ip.getHostName());
            //��ȡȫ�޶�������
            System.out.println(ip.getCanonicalHostName());
            //ʹ��IP��ַ��ȡInetAddressʵ����
            InetAddress lip=InetAddress.getByAddress(new byte[]{127,0,0,1});
            //�жϱ�����ַ�Ƿ�ɴ
            System.out.println(lip.isReachable(10));
            //ʹ��Ĭ�Ϸ�������ȡ����InetAddressʵ����
            InetAddress llip=InetAddress.getLocalHost();
            System.out.println(llip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










