package reflect;

import java.net.URL;

/**
 * Created by dtdyq on 2017/3/13.
 *  �����������.class�ļ����ص��ڴ��У���Ϊ֮������Ӧ��Class����
 *  һ���౻���ص�JVM��ͬһ���಻�ᱻ�ٴμ���
 *  ��JVM��һ��������ȫ�޶������������������Ϊ��Ψһ��ʶ
 *
 *  JVM����ʱ�����γ����������������ɵĳ�ʼ�����������νṹ��
 *      BootStrap ClassLoader��������������������java�����࣬������ClassLoader������
 *          ������JVM����ʵ�֣�
 *      Extension ClassLoader����չ����������������jre����չĿ¼��jar������
 *      System ClassLoader��ϵͳ���������������JVM����ʱ��������java�����
 *      -classpathѡ�java.class.pathϵͳ����
 */
public class TestClassLoader {
    public static void main(String[] args){
        //������������صĺ����ࣺ
        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url:urls){
            System.out.println(url);
        }
    }
}
