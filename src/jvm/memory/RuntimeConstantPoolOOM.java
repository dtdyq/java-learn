package jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dtdyq
 *
 *jvm����ʱ������OOM�쳣��
 *����ʱ�������Ƿ�������һ���֣�
 *����ͨ��string��intern����ʵ�ָ��쳣��intern��native�����������ǣ�����ַ���
 *���������Ѿ�������string��ͬ���ַ�������ֱ�ӷ��ظ��ַ��������򽫴��ַ�����ӵ�
 *��������
 *
 *�����ط��������ô�����java8֮ǰ����ͨ������-XX:PermSize��-XX:MaxPermSize����
 *��������С��java8��ʹ��Metaspace��JEP 122������־ô���PermGen space������JVM�������棬
 *ʹ��-XX:MetaSpaceSize��-XX:MaxMetaspaceSize����ԭ����-XX:PermSize��-XX:MaxPermSize��
 *
 *
 *��������
 *Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at jvm.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:23)
	
 *
 *������-XX:MetaspaceSize=10m   -XX:MaxMetaspaceSize=10m
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
