package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * �ռ���䵣����
 * �ڷ���minor gc֮ǰ��jvm����Ҫ�鿴������������������ռ䣬�Ƿ�������������ж���
 * ���������������minor gc�ǰ�ȫ�ģ���������������Ƿ����õ�HandlePromotionFailure
 * �Ƿ�������ʧ�ܣ���������������������������������������ռ��Ƿ��������
 * ������������Ķ����ƽ����С��������ڣ����Խ���һ��minor gc���������Handle-
 * PromotionFailure����Ϊ������ð�գ���ִ������full gc
 * 
 * java6�Ժ���ʹ�øò�����ֻҪ�����������������ڴ�ռ���������������ܴ�С������
 * ���������ƽ����С�������Minor gc���������Full GC
 *
 */
public class Memory_Allocat_Handle {
	private static final int _1MB = 1024*1024;
	@SuppressWarnings("unused")
	public static void main(String[] args){
	    byte[] a1,a2,a3,a4,a5,a6,a7;
	    a1 = new byte[2*_1MB];
	    a2 = new byte[2*_1MB];
	    a3 = new byte[2*_1MB];
	    
	    a1 = null;
	    
	    a4 = new byte[2*_1MB];
	    a5 = new byte[2*_1MB];
	    a6 = new byte[2*_1MB];
	    
	    a4 = null;
	    a5 = null;
	    a6 = null;
	    
	    a7 = new byte[2*_1MB];
	    
	}
}
