package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * Concurrent Mark Sweep收集器目标是获得最小的回收停顿时间，基于标记-清除算法
 * 运作过程：初始标记：CMS initial mark
 * 			并发标记：CMS concurrent markk
 * 			重新标记：CMS remark
 * 			并发清除：CMS concurrent sweep
 * 并发标记和并发清除都可以后用户线程同时工作，而初始标记和重新标记都不会占用太长时间
 * 缺点：1、该收集器对CPU资源十分敏感，在并发执行时，会占用比较大的CPU时间
 *		 2、无法处理浮动垃圾，可能出现“concurrent mode failure”失败而导致另一次full gc
 *		 产生。浮动垃圾是指在垃圾收集过程中用户程序产生的垃圾，这些垃圾只能在下次垃圾回
 *		收时被收集，因为在垃圾回收期间任然需要用户程序运行，因此需要预留出一部分空间，为
 *		可能新产生的对象分配内存，java5默认设置中，当老年代使用了68%的空间时，并发收集的
 *		程序就会起作用，如果运用中老年代增长不是很快，则可以适当调高-XX:CMSInitiating-
 *		OccupancyFraction的值来提高百分比，java6中，这一比值已经调高到92%，如果垃圾收
 *		集时间内，内存不够，就会发生“Concurrent mode failure”，而启动serial old进行
 *		处理。
 *		3、CMS使用标记清除算法，所以在垃圾收集后可能会存在大量的内存碎片，导致大的对象
 *		无法分配到内存，不得不再进行一次full gc。为解决这个问题，CMS设置了开关参数-XX:
 *		+UseCMSCompactAtFullCollection。用于在CMS收集器顶不住要进行full gc时开启内存
 *		碎片的合并整理过程，该过程中无法实现并发。CMS还有参数：-XX:CMSFullGCsBeforeCo-
 *		mpaction,用来设置执行多少次不压缩的Full gc后，执行一次压缩，默认为0
 *
 */
public class HotSpotGC_CMS {

}
