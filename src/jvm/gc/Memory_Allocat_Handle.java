package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * 空间分配担保：
 * 在发生minor gc之前，jvm首先要查看，老年代最大可用连续空间，是否大于新生代所有对象，
 * 如果条件成立，则minor gc是安全的，如果不成立，则看是否设置的HandlePromotionFailure
 * 是否允许担保失败，如果允许，如果允许，则继续检查老年代最大可用连续空间是否大于历次
 * 晋升到老年代的对象的平均大小，如果大于，则尝试进行一次minor gc，否则如果Handle-
 * PromotionFailure设置为不允许冒险，则执行依次full gc
 * 
 * java6以后不再使用该参数，只要老年代可用最大连续内存空间大于新生代对象总大小或历次
 * 晋升对象的平均大小，则进行Minor gc，否则进行Full GC
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
