package jvm.tool;

/**
 * 
 * @author dtdyq
 * 
 * jmap(Memory Map for Java)命令用于生成堆转储快照 。可以通过-HeapDumpOnCtrlBreak参数
 * 使用ctrl+break键让虚拟机生成dump文件
 * 
 * 还可以查询finalize执行队列，java堆和永久代的详细信息，如空间使用率、当前用的是哪种收
 * 集器
 * 
 *  命令格式：
 *  jmap [option] vmid
 *  option:
 *  	-dump:生成java堆转储快照
 *  	-finalizerinfo：显示在F-Queue中等待的Finalizer线程执行finalize方法的对象
 *  	-heap：显示java堆详细信息，如使用哪种回收器、参数配置、分代状况等
 *  	-histo：显示堆中对象统计信息，包括类、实例数量、合计容量
 *      -permstat:以ClassLoader为统计口径，显示永久代内存状态，
 *      -F:强制生成dump快照
 * eg：jmap -dump:format=b,file=dump.txt 4812
 **/

public class Tools_Jmap {
	
}





