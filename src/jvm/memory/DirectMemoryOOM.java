package jvm.memory;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 
 * @author dtdyq
 * 
 * directMemory可以通过设置-XX:MaxDirectMemorySize指定，如果不指定，则默认和-Xmx
 * 的值一样，
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
 * 由DirectMemory产生的异常，一个明显的特征是Heap dump文件不会看见明显的异常，
 * 如果发现OOM之后的dump文件很小，而程序又使用了NIO，则很可能是DirectMemory出现OOM
 */
public class DirectMemoryOOM {
	private static final int _1MB=1024*1024;
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException{
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe=(Unsafe)unsafeField.get(null);
		while(true){
			unsafe.allocateMemory(_1MB);
		}
	}

}
