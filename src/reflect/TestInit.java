package reflect;

/**
 * Created by dtdyq on 2017/3/13.
 *JVM������ء����Ӻͳ�ʼ����
 *  ���أ��ɼ�������ɣ�����Ӧ��class�ļ������ڴ�
 *      ��ϵͳ���������=����java.lang.class��ʵ����
 *      ����Դ�������ļ�ϵͳ��class�ļ�
 *             jar���м��ص�class�ļ�
 *             ͨ��������ص�class�ļ�
 *             ��java'�ļ���̬���벢����
 *  ���ӣ�����Ķ��������ݺϲ���jre�У�
 *      ��֤�����鱻���ص����Ƿ�����ȷ���ڲ��ṹ������������Э��һ��
 *      ׼��������Ϊ�������������ڴ棬������Ĭ�ϳ�ʼֵ
 *      �����������������ݵķ������ô���Ϊֱ������
 *  ��ʼ����
 *      ����û�б���ʼ�������ȳ�ʼ�����и���
 *      ��������г�ʼ����䣬��ִ�г�ʼ�����
 *
*/
public class TestInit {
    static{
        b=6;
        System.out.println("--------");
    }
    static int a=6;
    static int b=9;
    static int c;
    public static void main(String[] args){
        System.out.println(TestInit.b);
    }
}
