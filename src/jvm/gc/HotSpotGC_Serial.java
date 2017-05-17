package jvm.gc;
/**
 * @author dtdyq
 * 
 * serial收集器是最基本、发展历史最悠久的收集器，曾经是虚拟机新生代收集的唯一选择
 * 这是一个单线程收集器，它在进行垃圾收集时必须暂停所有其他的工作线程，直到收集结束
 * 优点：简单高效，对于运行在Client模式下的虚拟机来说是一个很好的选择
 */
public class HotSpotGC_Serial {

}

