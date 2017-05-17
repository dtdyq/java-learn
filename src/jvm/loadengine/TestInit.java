package jvm.loadengine;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 *
 * jvm�涨���������������������������г�ʼ����
 * 	1������new��getstatic��putstatic��invokestatic����ָ��ʱ
 * 	2��java.lang.reflect���µķ���������з������ʱ
 * 	3����ʼ��һ�����ʱ��������໹û�г�ʼ���������ȳ�ʼ������,�ӿڳ���
 * 	4��jvm����ʱ����������
 * 	5�����һ��java.lang.invoke.MethodHandleʵ�����Ľ������ref_getstatic��ref_putStatic��
 * 	ref_invokeStatic�ķ������
 * 
 * ���������ַ����⣬����������ķ��������ᴥ����ʼ������Ϊ��������
 * 
 */
public class TestInit {
	/**
	 * ����ʹ�����ֶ�֮һ��
	 * ͨ���������ø���ľ�̬�ֶΣ����ᵼ�������ʼ��
	 * ����HotSpot��ͨ��-XX:+TraceClassLoading��֪���౻���أ���δ��ʼ��
	 */
	@Test
	public void test1(){
		System.out.println(SubClass.value);
	}
	/**
	 * ����ʹ�����ֶ�֮����
	 * ͨ�������������࣬���ᵼ�´���ĳ�ʼ��
	 */
	@Test
	public void test2(){
		@SuppressWarnings("unused")
		SuperClass[] sc = new SuperClass[0];
	}
	/**
	 * ����ʹ�����ֶ�֮����
	 * �����ڱ���׶λ���������ĳ������У����ᴥ�����峣������ĳ�ʼ��
	 */
	@Test
	public void test3(){
		System.out.println(ConstClass.HELLO);
	}
	
}

class SuperClass{
	static{
		System.out.println("SuperClass.init()");
	}
	public static int value = 12;
}
class SubClass extends SuperClass{
	static{
		System.out.println("SubClass.init()");
	}
}

class ConstClass{
	static {
		System.out.println("ConstClass init");
	}
	public static final String HELLO = "hello world";
}














