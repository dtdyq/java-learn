package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * ��̬���������ж���
 * Ϊ�˸��õ�ʹ�ò�ͬ������ڴ������������������ܵȵ�������������MaxTenuringThreshold
 * ��ֵ�Ż�ŵ���������������survivor�ռ�����ͬ��������ж����С����survivor�ռ��С
 * ��һ��ʱ��������ڵ��ڸ����䷢�Ķ��󣬻�ֱ�ӽ����������������ȵ�����ﵽ
 * maxTenuringThreshold��ֵ
 * 
 *������
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
		a4 = new byte[4*_1MB];//ִ������gc��a1��a2�������һ��������survivor��
		a4 = null;
		//�ڶ���ִ��gc��a1��a2�������һ���ڴ�ʹ���survivor��һ�룬���Ƶ������
		a4 = new byte[4*_1MB];
	}
}
/*
 * ��־��
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






