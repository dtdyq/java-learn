package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * �����ֱ�ӽ��������
 * �������ָ��Ҫ�����ڴ�ռ��java���󣬵��͵Ĵ�����Ǻܳ����ַ����Լ����飬��������
 * ��������׵����ڴ滹�в��ٿռ�ʱ����ǰ���������ռ��Ի�ȡ�㹻�������ռ��������á�����
 * 
 * ������
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
    
    ��log���Կ�������3m�Ķ���ֱ�ӷ������������
 */













