package jvm.gc;
/**
 * -----有问题
 * @author dtdyq
 * 
 * 长期存活的对象将被进入老年区:虚拟机给每个对象定义了一个对象年龄计数器，如果对象在
 * eden区中经过第一次Minor GC后仍然存活，并且能被survivor区容纳，则会被移到survivor
 * 中，在survivor中没经过一次Minor GC没被收集，则年龄增加一岁
 * 超过MaxTenuringThreshold的值时，被转移到老年代
 *
 *-XX:MaxTenuringThreshold=1/15
 *-verbose:gc
 *-Xmx20m
 *-Xms20m
 *-Xmn10m
 *-XX:+PrintGCDetails
 *-XX:SurvivorRatio=8
 *-XX:+PrintTenuringDistribution
 */
public class Memory_Allocat_LongSurvivor {
	private static final int _1MB = 1024*1024;
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception{
		byte[] allocat1,allocat2,allocat3;
		allocat1 = new byte[_1MB/1024];
		
		allocat2 = new byte[4*_1MB];
		allocat3 = new byte[4*_1MB];
		allocat3 = null;
		allocat3 = new byte[4*_1MB];
	}
}
/*
 * MaxTenuringThreshold=1时输出的日志：
 * [GC (Allocation Failure) [DefNew: 5335K->794K(9216K), 0.0076977 secs] 5335K->4890K(19456K), 0.0078451 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
   [GC (Allocation Failure) [DefNew: 4890K->0K(9216K), 0.0024874 secs] 8986K->4889K(19456K), 0.0025414 secs] [Times: user=0.00 sys=0.02, real=0.00 secs] 
   Heap
     def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
     from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     tenured generation   total 10240K, used 4889K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     the space 10240K,  47% used [0x00000000ff600000, 0x00000000ffac6680, 0x00000000ffac6800, 0x0000000100000000)
     Metaspace       used 2651K, capacity 4486K, committed 4864K, reserved 1056768K
     class space    used 285K, capacity 386K, committed 512K, reserved 1048576K

 * MaxTenuringThreshold=15时输出的日志：
 * [GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 15)
- age   1:     552416 bytes,     552416 total
: 5079K->539K(9216K), 0.0082064 secs] 5079K->4635K(19456K), 0.0083473 secs] [Times: user=0.00 sys=0.02, real=0.01 secs] 
[GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 15 (max 15)
: 4635K->0K(9216K), 0.0025544 secs] 8731K->4634K(19456K), 0.0026068 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
  def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
  tenured generation   total 10240K, used 4634K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
  the space 10240K,  45% used [0x00000000ff600000, 0x00000000ffa86a80, 0x00000000ffa86c00, 0x0000000100000000)
  Metaspace       used 2651K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 285K, capacity 386K, committed 512K, reserved 1048576K

 */













