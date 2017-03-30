package reflect;

import java.net.URL;
import java.util.Enumeration;

/**
 * Created by dtdyq on 2017/3/13.
 *  ����ػ��ƣ�
 *      ȫ�̸��𣺵�һ�������������ĳ��classʱ����class�����������õ�����class
 *               Ҳ���ɸ����������������
 *      ����ί�У����ø����������ͼ���ظ�class��ֻ���ڸ���������޷��޷����ظ�
 *               ��ʱ���Ż᳢�Դ��Լ�����·�����м���
 *      ������ƣ���֤���м��ع���class���ᱻ���棬��������Ҫʹ��ĳ��classʱ���������
 *               ���ȴӻ�������������class��ֻ���ڻ��������Ҳ�������ʱ��ϵͳ�Ż��ȡ
 *               ����Ķ��������ݣ�������ת��Ϊclass���󣬴��뻺������
 *  �û�Ҳ����ͨ���̳�ClassLoader��ʵ���Զ�����������
 *
 *
 *  ����ز��裺
 *      1������class�Ƿ񱻼��ع�������ֱ�ӵ��ڰ˲���
 *      2�������������������ڣ��򵽵��Ĳ�ִ��
 *      3������ʹ�ø������������Ŀ���࣬����ɹ��򵽵ڰ˲���������岽
 *      4������������������Ŀ����ɹ��򵽵ڰ˲������򵽵��߲�
 *      5����ǰ�������Ѱ��class�ļ�������ҵ�����������򵽵��߲�
 *      6�����ļ�������class���ɹ�����󵽵ڰ˲�
 *      7���׳�ClassNotFoundException�쳣
 *      8�����ض�Ӧ��class����
 */
public class TestClassLoadProp {
    public static void main(String[] args){
        try{
            ClassLoader systemloader=ClassLoader.getSystemClassLoader();
            System.out.println("system classloader:"+systemloader);

            Enumeration<URL> eml=systemloader.getResources("");
            while(eml.hasMoreElements()){
                System.out.println(eml.nextElement());
            }
            //��ȡϵͳ��������ĸ�����������õ���չ�������
            ClassLoader extensionloader=systemloader.getParent();
            System.out.println("extension classloader:"+extensionloader);
            System.out.println("path of extension classLoader:"+extensionloader.getParent());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
