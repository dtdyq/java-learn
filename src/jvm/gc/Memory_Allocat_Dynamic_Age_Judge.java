package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * 动态对象年龄判定：
 * 为了更好的使用不同程序的内存情况，虚拟机并不是总等到对象的年龄大于MaxTenuringThreshold
 * 的值才会放到老年代，而是如果survivor空间中相同年龄的所有对象大小大于survivor空间大小
 * 的一半时，年龄大于等于该年龄发的对象，会直接进入老年代，而不会等到年龄达到
 * maxTenuringThreshold的值
 * 
 *参数：
 *	-XX:+UseSerialGC
 *  -verbose:gc
	-Xmx20m
	-Xms20m
	-Xmn10m
	-XX:+PrintGCDetails
	-XX:SurvivorRatio=8
	-XX:MaxTenuringThreshold=15
 */
public class Memory_Allocat_Dynamic_Age_Judge {
	private static final int _1MB = 1024*1024;
	@SuppressWarnings("unused")
	public static void main(String[] args){
		byte[] a1,a2,a3,a4;
		a1 = new byte[_1MB/4];
		a2 = new byte[_1MB/4];
		a3 = new byte[4*_1MB];
		a4 = new byte[4*_1MB];//执行依次gc，a1和a2的年龄加一，被放入survivor区
		a4 = null;
		//第二次执行gc，a1和a2的年龄加一，内存和大于survivor的一半，被移到老年代
		a4 = new byte[4*_1MB];
	}
}
/*
 * 日志：
 * [GC (Allocation Failure) [DefNew: 5591K->1024K(9216K), 0.0086280 secs] 5591K->5146K(19456K), 0.0087587 secs] [Times: user=0.00 sys=0.01, real=0.01 secs] 
   [GC (Allocation Failure) [DefNew: 5120K->0K(9216K), 0.0036305 secs] 9242K->5146K(19456K), 0.0037109 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
   Heap
     def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
     from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     tenured generation   total 10240K, used 5146K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     the space 10240K,  50% used [0x00000000ff600000, 0x00000000ffb068e0, 0x00000000ffb06a00, 0x0000000100000000)
     Metaspace       used 2651K, capacity 4486K, committed 4864K, reserved 1056768K
     class space    used 285K, capacity 386K, committed 512K, reserved 1048576K

 */






