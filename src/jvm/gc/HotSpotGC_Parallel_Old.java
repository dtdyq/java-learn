package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * parallel old收集器是parallel scavenge收集器的老年版本，使用多线程和标记-整理算法
 * 在java6开始提供，主要用于代替serial old在老年代的作用：配合parallel scavenge进行
 * 垃圾收集，因为parallel scavenge不能和CMS配合使用，这两种收集器配合使用实现了真正的
 * 吞吐量优先，在注重吞吐量和CPU资源敏感的场合中，可以优先考虑parallel scavenge和
 * parallel old收集器进行垃圾收集
 *
 */
public class HotSpotGC_Parallel_Old {
	
}
