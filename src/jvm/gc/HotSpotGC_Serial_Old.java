package jvm.gc;
  
/**
 *  
 * @author dtdyq
 * 
 * 该收集器是serial收集器的老年代版本，同样是一个单线程收集器，使用标记整理算法，该收集器
 * 主要用于在client下的jvm使用
 * 
 * 在server模式下，它还有两大用途：在java5之前的版本中和parallel scavenge配合使用，另一种
 * 用途是作为CMS收集器的后备预案，在并发收集发生Concurrent Mode Failure时使用
 * 
 **/

public class HotSpotGC_Serial_Old {
	
}