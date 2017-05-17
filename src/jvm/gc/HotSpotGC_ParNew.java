package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * ParNew收集器是serial收集器的多线程版本，是许多运行在Server模式下的虚拟机中
 * 首选的新生代收集器，一个比较重要的原因是，目前除了serial收集器外，只有该收集器
 * 能和CMS收集器配合，
 * ParNew收集器也是使用-XX:+UseConcMarkSweepGC选项后的默认收集器
 * 也可以通过参数-XX:+UseParNewGC强制使用
 * 
 * ParNew在单CPU的环境中不会有比serial更好的效果，该收集器默认开启的线程数是机器的
 * CPU数，可以通过使用-XXParallelGCThreads参数指定收集的线程数
 *
 */
public class HotSpotGC_ParNew {

}
