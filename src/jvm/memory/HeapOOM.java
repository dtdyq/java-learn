package jvm.memory;

import java.util.ArrayList;
import java.util.List;


/**
 * java堆溢出：
 * vm args:-Xmx20m   -Xms20m   -XX:+HeapDumpOnOutOfMemoryError
 * 最后一个参数可以让jvm在出现内存溢出异常时dump出当前内存堆转储快照以便以后分析
 * 
 * 运行结果：
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
	
	解决该区域的异常时，一般先使用内存映像工具(eclipse memory analyzer)对dump出的
	堆转储快照进行分析，重点时确认内存中的对象是否是有必要的，即时发生了内存泄漏(memory
	leak)还是内存溢出(memory overfow),
	如果是ml，则进一步通过工具查找到对象的gc roots的饮用链，找到泄漏对象是怎么关联到gc
	roots导致对象无法被回收
	如果是mo，则需要看情况是否调大-Xmx来改善情况或者是否有存在时间过长的对象
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