package jvm.loadengine;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 *
 * jvm规定了以下五种情况必须立即对类进行初始化：
 * 	1、遇到new、getstatic、putstatic、invokestatic四条指令时
 * 	2、java.lang.reflect包下的方法对类进行反射调用时
 * 	3、初始化一个类的时候，如果父类还没有初始化，则首先初始化父类,接口除外
 * 	4、jvm启动时，加载主类
 * 	5、如果一个java.lang.invoke.MethodHandle实例最后的解析结果ref_getstatic、ref_putStatic、
 * 	ref_invokeStatic的方法句柄
 * 
 * 处以上五种方法外，所有引用类的方法都不会触发初始化，称为被动引用
 * 
 */
public class TestInit {
	/**
	 * 被动使用类字段之一：
	 * 通过子类引用父类的静态字段，不会导致子类初始化
	 * 对于HotSpot，通过-XX:+TraceClassLoading可知子类被加载，但未初始化
	 */
	@Test
	public void test1(){
		System.out.println(SubClass.value);
	}
	/**
	 * 被动使用类字段之二：
	 * 通过数组来引用类，不会导致此类的初始化
	 */
	@Test
	public void test2(){
		@SuppressWarnings("unused")
		SuperClass[] sc = new SuperClass[0];
	}
	/**
	 * 被动使用类字段之三：
	 * 常量在编译阶段会存入调用类的常量池中，不会触发定义常量的类的初始化
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














