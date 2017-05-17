package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * 大对象直接进入老年代
 * 大对象是指需要连续内存空间的java对象，典型的大对象是很长的字符串以及数组，经常出现
 * 大对象容易导致内存还有不少空间时就提前触发垃圾收集以获取足够的连续空间来“安置”它们
 * 
 * 参数：
 * -verbose:gc -Xmx20m  -Xms20m  -Xmn10m  -XX:PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728
 */
public class Memory_Allocat_BigObject {
	@SuppressWarnings("unused")
	public static void main(String[] args){
		byte[] allocat;
		allocat = new byte[4*1024*1024];
	}
}
/*
 * Heap
    def new generation   total 9216K, used 1147K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
    eden space 8192K,  14% used [0x00000000fec00000, 0x00000000fed1ef70, 0x00000000ff400000)
    from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
    to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
    tenured generation   total 10240K, used 4096K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
    the space 10240K,  40% used [0x00000000ff600000, 0x00000000ffa00010, 0x00000000ffa00200, 0x0000000100000000)
    Metaspace       used 2651K, capacity 4486K, committed 4864K, reserved 1056768K
    class space    used 285K, capacity 386K, committed 512K, reserved 1048576K
    
    由log可以看出超过3m的对象直接分配在了老年代
 */













