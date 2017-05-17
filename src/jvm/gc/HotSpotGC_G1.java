package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * garbage-first是面向服务端运用的垃圾收集器，
 * 特点：
 * 		并行与并发：充分利用多CPU，尽量减少停顿时间
 * 		分代收集：g1可以不用和其他收集器协同，而独立的完成整个gc
 * 		空间整合：g1从整体来看是标记-整理算法，但从局部看是复制算法，使得gc运行期间不会
 * 				 产生内存碎片
 * 		可预测的停顿：g1除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指
 * 					 定一个长度为M毫秒的时间片内，消耗在gc上的时间不超过N毫秒
 * 
 * g1使用了化整为零的收集策略，不再在物理上区分新生代和老年代，而是讲gc区域划分为小的
 * region，每次只对最值得进行垃圾回收的region进行收集，
 * 一个region中的对象可能引用了另一个region中的对象，未避免每次进行全局扫描，jvm为每个
 * region设立了remembered set来记录来自别的region对象对本region的对象的引用，这样就不
 * 需要对整个region进行扫描
 * 
 * g1运作步骤：
 * 		初始标记 Initial marking
 * 		并发标记：concurrent marking
 * 		最终标记：final marking
 * 		筛选回收：live data counting and evacuation
 * ********************************************************************************
 * 追求低停顿，g1是不错的选择，但是追求吞吐量的话，g1目前比起CMS并没有什么优势
 *
 */
public class HotSpotGC_G1 {

}
