package jvm.gc;
/**
 * 
 * @author gc日志的理解：
 * 
 *  0.382: [GC (System.gc()) [PSYoungGen: 3891K->648K(35840K)] 3891K->656K(117760K),
 *  0.0029518 secs] [Times: user=0.00 sys=0.02, real=0.01 secs] 
 *  
 *  0.387: [Full GC (System.gc()) [PSYoungGen: 648K->0K(35840K)] [ParOldGen: 
 *  8K->537K(81920K)] 656K->537K(117760K), [Metaspace: 2645K->2645K(1056768K)], 
 *  0.0157405 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
 *	
 * 最前面的数字0.382和0.0157405代表gc发生的时间，是从java虚拟机启动时间开始经过的秒数
 * 
 * 开头的GC 和Full GC代表这次垃圾收集的停顿类型，如果有full，说明gc是发生了stop-the-
 * world的。（System.gc()）表示系统给调用了System.gc()
 * 
 * 接下来的PSYoungGen、ParOldGen、Metaspace代表了gc发生的区域，显示的区域名称和使用的
 * gc收集器类型有很大关系，PSYoungGen表示使用parallel scavenge收集器，DefNew(default
 * new generation)使用serial收集器，如果使用ParNew收集器，则名字会变为ParNew。老年代
 * 命名方式相同。
 * 
 *  方括号中的3891K->648K(35840K)是指：GC前该内存区域已使用容量->GC后该内存已使用容量
 *  (该内存区域总容量)。而方括号之外的3891K->656K(117760K)表示：gc前java堆已使用容量
 *  ->gc后java堆使用容量(java堆总容量) 。再往后的时间表示gc在该内存区域所占用的秒数
 *  
 *  [Times: user=0.06 sys=0.00, real=0.02 secs] 是更详细的时间数据：分别表示用户态
 *  消耗CPU时间，内核态消耗CPU时间和操作从开始到结束的墙钟时间(Wall Clock Time)。
 *  CPU时间和墙钟时间的区别是：后者包括各种非运算的等待耗时，包括等待IO和等待线程阻塞
 *  
 */
public class GC_Log {

}
