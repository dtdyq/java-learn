package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * gc参数：
 * 
 * 			参数							                         描述
 * *******************************************************************************
 *        UseSerialGC      *    jvm运行在Client下的默认值，打开后使用Serial+Serial Old
 *        				   *    进行收集
 *********************************************************************************
 *	  	UseParNewGC        *    使用ParNew和Serial Old进行垃圾回收
 **********************************************************************************
 *   UseConcMarkSweepGC    *    打开此开关后使用ParNew+Serial Old+CMS收集器进行收集
 *						   *	serial old作为CMS的后备收集器使用
 **********************************************************************************
 *     UseParallelGC       *    虚拟机运行在server模式下的默认值，打开此开关后使用
 *     					   *    Parallel Sacvenge+Serial Old进行垃圾回收
 **********************************************************************************
 *	   UseParallelOldGC    *    打开此开关后使用Parallel scavenge和parallel old进行
 *						   *    回收
 **********************************************************************************
 *      SurvivorRadio      *    新生代中Eden区域和Survivor区域的容量比值，默认为8
 **********************************************************************************
 * PretenureSizeThreshold  *    直接晋升到老年代的对象大小，设置这个参数后，大于这个值
 * 						   *    的对象将直接分配到老年代
 **********************************************************************************
 *  MaxTenuringThreshold   *    晋升到老年代对象的年龄，每个对象在坚持过一个Monor GC
 *  					   *    后，年龄就加一，超过这个参数则被划进老年代
 **********************************************************************************
 * UseAdaptiveSizePolicy   *	动态调整java堆中各个区域的大小和对象进入老年代的时间
 **********************************************************************************
 * HandlePromotionFailure  *    是否允许分配担保失败，即老年代的剩余空间不足以应付新生代
 * 						   *    的整个Eden和survivor区的所有对象都存活的极端情况
 **********************************************************************************
 *    ParallelGCThread     *	设置并行GC时内存回收的线程数
 **********************************************************************************
 *    GCTimeRadio          *	gc时间与总时间的比例，默认为99，仅在使用Parallel 
 *    					   *    Scavenge时有效
 **********************************************************************************
 *    MaxGCPauseMillis     *	设置最大gc停顿时间，仅在Parallel scavenge时有效
 **********************************************************************************
 *  CMSInitiatingOccup-    *	设置CMS收集器在在老年代空间被使用都少后触发gc，仅在使用
 *  ancyFraction		   *	CMS时有效
 *********************************************************************************
 *  UseCMSCompactAtFull-   *    设置CMS收集器在执行完垃圾收集时是否进行内存碎片整理
 *     Collection		   *	仅在CMS使用时有效
 *********************************************************************************
 * CMSFullGCsBefore-       *    设置多少次CMS垃圾回收后进行一次内存碎片整理，仅在使用
 *     Compaction           *    CMS时有效
 */
public class GC_Args {

}
