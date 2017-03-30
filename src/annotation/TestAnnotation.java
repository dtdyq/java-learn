package annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * Annotation与一般注释不同，是代码中的一种特殊标记
 * 可以在编译、类加载、运行时被读取，Annotation相当
 * 于一种修饰符，可用于修饰包、类、构造器、方法、成员变量、参数和局部变量
 * 
 * 访问和处理Annotation的工具被称为Annotation Porcessing Tool(APT)
 * 
 * 五个基本的Annotation：
 * 		@ Override	重写父类方法
 * 		@ deprecated		标识已过时
 * 		@ SuppressWarning	抑制编译器警告
 * 		@ SafeVarargs	java7的“堆污染”警告
 * 		@ FunctionalInterface    java8用来指定某个接口必须是函数式接口(
 * 									只有默认方法、static方法和一个抽象方法)
 * @author dtdyq
 *
 */
public class TestAnnotation {
	public static void main(String[] args) {
		faultMethod(new List[3]);
	}
	@SafeVarargs
	public static void faultMethod(List<String>...listArray){
		List[] list=listArray;
		List<Integer> myList=new ArrayList<>();
		myList.add(new Random().nextInt(100));
		list[0]=myList;
		System.out.println(list[0].get(0));
	}
}

@FunctionalInterface//只能用于接口
interface FuncInterface{
	static void foo(){
		System.out.println("foo moethd");
	}
	default void bar(){
		System.out.println("bar method");
	}
	void test();
}



