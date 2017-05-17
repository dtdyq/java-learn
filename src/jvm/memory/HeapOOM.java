package jvm.memory;

import java.util.ArrayList;
import java.util.List;


/**
 * java�������
 * vm args:-Xmx20m   -Xms20m   -XX:+HeapDumpOnOutOfMemoryError
 * ���һ������������jvm�ڳ����ڴ�����쳣ʱdump����ǰ�ڴ��ת�������Ա��Ժ����
 * 
 * ���н����
 *  java.lang.OutOfMemoryError: Java heap space
	Dumping heap to java_pid2532.hprof ...
	Heap dump file created [27991529 bytes in 0.325 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181) 
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at jvm.TestOOM.main(TestOOM.java:18)
	
	�����������쳣ʱ��һ����ʹ���ڴ�ӳ�񹤾�(eclipse memory analyzer)��dump����
	��ת�����ս��з������ص�ʱȷ���ڴ��еĶ����Ƿ����б�Ҫ�ģ���ʱ�������ڴ�й©(memory
	leak)�����ڴ����(memory overfow),
	�����ml�����һ��ͨ�����߲��ҵ������gc roots�����������ҵ�й©��������ô������gc
	roots���¶����޷�������
	�����mo������Ҫ������Ƿ����-Xmx��������������Ƿ��д���ʱ������Ķ���
 */
public class HeapOOM{
	static class OOMObject{
		static OOMObject[] oom=new OOMObject[1000];
		
	}
	public static void main(String[] args){
		List<OOMObject> oom=new ArrayList<>();
		while(true){
			oom.add(new OOMObject());
		}
	}
}