package jvm.gc;
/**
 * ����
 * 	Minor GC���������������գ��Ƚ�Ƶ��
 * 	Full GC ����������������գ��ٶȱ�Monor GC��һ��������
 * 
 * @author dtdyq
 * 
 * ������������������Eden������з���
 * 
 * ������
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
		//eden�����Ѿ���ʹ����6m��ʣ��2m
		//���²�����ʹϵͳ����һ��gc����Ϊeden�Ŀռ䲻��
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
 * ����־���Կ�������Ϊallocat4�����ڴ�ʱ��eden�ռ��Ѿ����㣬ֻ��ͨ�����䵣�����ƣ���
 * allocat1-3ת�Ƶ������(the space),
 * �����eden��ռ����һ��(��8m��allocat4ռ����4m)��the space(�����)��ռ����6m�����ڶ���
 * allocat1-3��������ʾ used 60%��
 */



