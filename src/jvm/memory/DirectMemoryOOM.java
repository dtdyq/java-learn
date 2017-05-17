package jvm.memory;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 
 * @author dtdyq
 * 
 * directMemory����ͨ������-XX:MaxDirectMemorySizeָ���������ָ������Ĭ�Ϻ�-Xmx
 * ��ֵһ����
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
 * ��DirectMemory�������쳣��һ�����Ե�������Heap dump�ļ����ῴ�����Ե��쳣��
 * �������OOM֮���dump�ļ���С����������ʹ����NIO����ܿ�����DirectMemory����OOM
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
