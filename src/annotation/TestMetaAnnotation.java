package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java.lang.annnoation包下面提供了6个Meta Annotation专门用于处理java8新增的重复注释
 * 
 * @ Retention：只能用于修饰Annotation定义，用于指定修饰的Annotation可以保留多长时间
 * 该修饰的值共有三个：
 * 		RetentionPolicy.CLASS:编译器将Annotation记录在class文件中，当运行java程序时
 * 							  不可获取Annotation，这是默认值
 * 		RetentionPolicy.RUNTIME:编译器将Annotation保留在class中，运行java文件时，可以
 * 								通过反射获取Annotation信息
 * 		RetentionPolicy.SOURCE:Annotation只保留在源代码中，编译器直接丢弃Annotation
 * 
 * 
 * @ Target:只能修饰Annotation定义，用于指定被修饰的Annotation能用于修饰哪些程序单元
 * 值有以下几种：
 * 		ElementType.ANNOTATION_TYPE 指定该策略的Annotation只能修饰Annotation
 * 		ElementType.CONSTRUCTOR  只能修饰构造器
 * 		ElementType.FIELD   只能修饰成员变量
 * 		ElementType.LOCAL_VARIABLE   只能修饰局部变量
 * 		ElementType.METHOD   只能修饰方法
 * 		ElementType.PACKAGE  只能修饰包
 * 		ElementType.PARAMETER   只能修饰参数
 * 		ElementType.TYPE   可以修饰类、接口或枚举定义
 * 
 * 
 * @ Documented : 用于指定被该Meta Annotation修饰的Annotation类将会被提取成文档
 * 
 * 
 * @ Inherited： 被该Meta Annotation修饰的Annotation如果出现在某个类中，则它的子类
 * 自动被该Annotation修饰
 * @author dtdyq
 *
 */
public class TestMetaAnnotation {
	//该Annotation将会在生成的doc中出现
	@Docable
	public void foo(){
		System.out.println("foo...");
	}
	public static void main(String[] args) {
		
	}
}

@Retention(RetentionPolicy.CLASS)
@interface Testable{}

@Target(ElementType.TYPE)
@interface targetable{}

@Documented
@interface Docable{}

@Inherited
@interface Inheritable{}

@Inheritable
class F{}
class C extends F{
	@org.junit.Test
	void Test(){
		//程序输出为true，当父类F不再使用@ Inheritable修饰时，将输出false
		System.out.println(C.class.isAnnotationPresent(Inheritable.class));
	}
}







