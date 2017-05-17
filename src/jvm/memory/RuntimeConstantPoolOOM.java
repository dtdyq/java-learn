package jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dtdyq
 *
 *jvm运行时常量池OOM异常：
 *运行时常量池是方法区的一部分，
 *可以通过string的intern方法实现该异常，intern是native方法，作用是：如果字符串
 *常量池中已经包含该string相同的字符串，则直接返回该字符串，否则将此字符串添加到
 *常量池中
 *
 *常量池分配在永久代，在java8之前可以通过设置-XX:PermSize和-XX:MaxPermSize限制
 *方法区大小。java8中使用Metaspace（JEP 122）代替持久代（PermGen space）。在JVM参数方面，
 *使用-XX:MetaSpaceSize和-XX:MaxMetaspaceSize代替原来的-XX:PermSize和-XX:MaxPermSize。
 *
 *
 *输出结果：
 *Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at jvm.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:23)
	
 *
 *参数：-XX:MetaspaceSize=10m   -XX:MaxMetaspaceSize=10m
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args){
		List<String> list = new ArrayList<>();
		int i = 0;
		while(true){
			list.add(String.valueOf("javascript"+i).intern());
		}
	}
}
