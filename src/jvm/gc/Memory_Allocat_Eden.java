package jvm.gc;
/**
 * 附：
 * 	Minor GC：新生代垃圾回收，比较频繁
 * 	Full GC ：老年代的垃圾回收，速度比Monor GC慢一个数量级
 * 
 * @author dtdyq
 * 
 * 对象优先在新生代的Eden区域进行分配
 * 
 * 参数：
 * -XX:+UseSerialGC
 * -verbose:gc  -Xms20m  -Xmx20m  -Xmn10m  -XX:+PrintGCDetails -XX:SurvivorRadio=8
 * -Xloggc:f:/gclog/Memory_Allocat_Eden-gc.log
 */
public class Memory_Allocat_Eden {
	private static final int _1MB=1024*1024;
	@SuppressWarnings("unused")
	public static void main(String[] args){
		byte[] allocat1,allocat2,allocat3,allocat4;
		allocat1 = new byte[2*_1MB];
		allocat2 = new byte[2*_1MB];
		allocat3 = new byte[2*_1MB];
		//eden区域已经被使用了6m，剩余2m
		//以下操作会使系统发生一次gc，因为eden的空间不足
		allocat4 = new byte[4*_1MB];
		
	}

}
/*
 * log:
 * CommandLine flags: -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:MaxNewSize=10485760 -XX:NewSize=10485760 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC 
   0.370: [GC (Allocation Failure) 0.371: [DefNew: 7127K->538K(9216K), 0.0120517 secs] 7127K->6682K(19456K), 0.0127288 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
   Heap
     def new generation   total 9216K, used 4716K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
     from space 1024K,  52% used [0x00000000ff500000, 0x00000000ff5868d8, 0x00000000ff600000)
     to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     tenured generation   total 10240K, used 6144K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     the space 10240K,  60% used [0x00000000ff600000, 0x00000000ffc00030, 0x00000000ffc00200, 0x0000000100000000)
     Metaspace       used 2651K, capacity 4486K, committed 4864K, reserved 1056768K
     class space    used 285K, capacity 386K, committed 512K, reserved 1048576K
 * 
 * 由日志可以看出，在为allocat4分配内存时，eden空间已经不足，只好通过分配担保机制，将
 * allocat1-3转移到老年代(the space),
 * 结果是eden被占用了一半(共8m，allocat4占用了4m)，the space(老年代)被占用了6m，用于对象
 * allocat1-3，所以显示 used 60%；
 */



