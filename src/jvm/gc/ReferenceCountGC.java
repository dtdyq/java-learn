package jvm.gc;
/**
 * 
 * @author dtdyq
 * ���ü�����ʵ����������
 * ���ü�����ͨ���������Ƿ����������жϻ���ʱ��
 * ������ʱ����Ϊѭ�����ö������Ѿ�û�õĶ���ò������գ�������ĳ���
 *
 *��¼gc��־�Ĳ�����
 *-XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:f:/gclog/ReferenceCountGC-gc.log
 *
 *���к����־���ݣ�
 *
 *  Java HotSpot(TM) 64-Bit Server VM (25.121-b13) for windows-amd64 JRE (1.8.0_121-b13), built on Dec 12 2016 18:21:36 by "java_re" with MS VC++ 10.0 (VS2010)
	Memory: 4k page, physical 7841140k(4527908k free), swap 9086324k(4867620k free)
	CommandLine flags: -XX:InitialHeapSize=125458240 -XX:MaxHeapSize=2007331840 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
	0.382: [GC (System.gc()) [PSYoungGen: 3891K->648K(35840K)] 3891K->656K(117760K), 0.0029518 secs] [Times: user=0.00 sys=0.02, real=0.01 secs] 
	0.387: [Full GC (System.gc()) [PSYoungGen: 648K->0K(35840K)] [ParOldGen: 8K->537K(81920K)] 656K->537K(117760K), [Metaspace: 2645K->2645K(1056768K)], 0.0157405 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
	Heap
	  PSYoungGen      total 35840K, used 307K [0x00000000d8180000, 0x00000000da980000, 0x0000000100000000)
	  eden space 30720K, 1% used [0x00000000d8180000,0x00000000d81cce40,0x00000000d9f80000)
	  from space 5120K, 0% used [0x00000000d9f80000,0x00000000d9f80000,0x00000000da480000)
	  to   space 5120K, 0% used [0x00000000da480000,0x00000000da480000,0x00000000da980000)
	  ParOldGen       total 81920K, used 537K [0x0000000088400000, 0x000000008d400000, 0x00000000d8180000)
	  object space 81920K, 0% used [0x0000000088400000,0x0000000088486708,0x000000008d400000)
	  Metaspace       used 2652K, capacity 4486K, committed 4864K, reserved 1056768K
	  class space    used 285K, capacity 386K, committed 512K, reserved 1048576K
 */
public class ReferenceCountGC {
	public Object inst=null;
	private static final int _1MB=1024*1024;
	@SuppressWarnings("unused")
	private byte[] bigSize=new byte[_1MB];
	public static void main(String[] args){
		ReferenceCountGC a = new ReferenceCountGC();
		ReferenceCountGC b = new ReferenceCountGC();
		a.inst = b;
		b.inst = a;
		a = null;
		b = null;
		System.gc();
	}
}
