package jvm.tool;

import java.util.concurrent.TimeUnit;

/**
 * @author dtdyq
 * 
 * jstat(jvm statistics monitoring tool):用于监视虚拟机各种运行状态信息的命令行工具
 * 可以显示本地或远程的虚拟机进程中的类装载、内存、垃圾收集、jit编译等运行参数
 * 命令格式：
 * jstat [option vmid [interval[s|ms] [count]] ]
 * vmid如果在本地的话，则和lvmid相同，如果是远程主机，则格式为：
 * 	[protocol:][//]lvmid[@hostname[:port]/servername]
 * 
 * internal 和 count代表查询间隔和次数，若果省略，说明只查询一次
 * option:
 *     -class:监视类装载卸载数量、总空间以及装载类所消耗的时间
 *     -gc：监视java堆状况，包括Eden区、两个Survivor区，老年代，永久性等的容量、已用
 *          空间，gc时间合计等信息
 *     -gccapacity:监视内容与-gc基本相同，但输出主要关注java堆各个区域使用的最大、最
 *     		小空间
 *     -gcutil：监视内容与-gc基本相同，但输出主要关注已使用的空间占总空间的百分比
 *     -gccause:与上面的功能一样，但是会额外输出导致上次gc产生
 *	   -gcnew：监视新生代gc状况
 *	   -gcnewcapacity:监视内容与-gcnew基本相同，输出主要关注使用的最大、最小空间
 *	   -gcold：监视老年代gc状况
 *	   -gcoldcapacity:和上面的监视内容基本相同，输出主要关注使用的最大。大小空间
 *	   -gcpermcapacity:输出永久代使用到的最大最小空间
 *	   -compiler:输出jit编译器编译过的方法、耗时等信息
 *	   -printcompilation:输出已被jit编译的方法
 *	   
 */

public class Tools_Jstat {
	public static void main(String[] args) throws Exception{
		while(true){
			Class.forName("jvm.tool.Tools_Jstat");
			TimeUnit.SECONDS.sleep(10);
			Class.forName("jvm.tool.Tools_Jps");
		}
	}
}
