package reflect;

/**
 * Created by dtdyq on 2017/3/13.
 *
 * ���ʼ����ʱ�䣺java�����״�ͨ���������ַ�ʽʹ��ĳ�����ӿ�ʱ����ִ�г�ʼ����
 *      �������ʵ����new�ؼ��֣�ͨ������������ʵ����ͨ�������л��ഴ��ʵ��
 *      ����ĳ������෽������̬������
 *      ����ĳ���ӿڻ���������
 *      ʹ�÷��䷽ʽǿ�ƴ���ĳ�����ӿڶ�Ӧ��java.lang.class����
 *      ֱ��ͨ��java.exe������ĳ������
 *      ��ʼ��ĳ���������
 *
 * final���ε��������������������ֵ�ڱ���ʱ�Ϳ���ȷ����������
 * ���������൱�ڡ����������java���������ڱ���ʱֱ�ӰѸ��������
 * �ֵĵط��滻������ֵ�����ᵼ�¸���ĳ�ʼ����
 * ����������ڱ���ʱ����ȷ������������Ҫ������г�ʼ����
 */
public class TestInitTime {
    public static void main(String[] args) throws ClassNotFoundException{
        //���ᵼ��CompileConstant�ĳ�ʼ����
        System.out.println(CompileConstant.compileConstant);
        //�ᵼ��CompileConstant�ĳ�ʼ����
        System.out.println(CompileConstant.compile);

        ClassLoader cl=ClassLoader.getSystemClassLoader();;
        cl.loadClass("Loader");

        Class.forName("Loader");
    }
}
class CompileConstant{
    static {
        System.out.println("static field...");
    }
    static final String compileConstant="staticfinaltype";
    static final String compile=System.currentTimeMillis()+"---";
}
/**
 * ��ʹ��ClassLoader���loadClass()����ʱ���÷���ֻ�Ǽ����࣬����ִ�г�ʼ����
 * ʹ��Class.forName()ʱ�ᵼ��ǿ�Ƴ�ʼ������
 */
class Loader{
    static{
        System.out.println("initilize...");
    }
}