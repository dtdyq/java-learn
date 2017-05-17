package jvm.memory;

import java.lang.reflect.Method;

import jvm.memory.HeapOOM.OOMObject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author dtdyq
 * 
 * java方法区OOM异常：
 * 方法区用于存放class的相关信息，如类名、访问修饰符、常量池、字段描述和方法描述
 * 
 * 可通过运行时产生大量的类来填满方法区，也可以借助CGLib直接操作字节码使运行时产生
 * 大量的类
 * 
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at jvm.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:39)

 *
 *由结果可以看出，显示的是Java heap space出现OOM异常
 *java8中已经没有永久代这一概念，方法区在概念上依旧存在，不过类的相关元信息
 *已经被放在了直接从内存分配的区域，而类的静态变量和字符串等分配在堆中
 *
 */
public class JavaMethodAreaOOM {
	public static void main(String[] args){
		while(true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor(){

				@Override
				public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
					
					return arg3.invoke(arg0,arg2);
				}
				
			});
			enhancer.create();
		}
	}

}
