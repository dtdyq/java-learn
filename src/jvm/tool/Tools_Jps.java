package jvm.tool;
/**
 * 
 * @author dtdyq
 * 
 * sun jdk常用监控和故障处理工具
 * 
 * jps：jvm Process Status Tool，显示指定系统的所有HotSpot虚拟机进程
 * jps [options] [hostid]
 * 参数：
 * 		-q：只输出LVMID(Local Virtual Machine Identifier)，省略主类的名称
 * 		-m：输出虚拟机进程启动时传给主类函数的参数
 * 		-l：输出主类全名或jar包路径
 * 		-v：输出虚拟机进程启动时的jvm参数
 *
 */
public class Tools_Jps {
	public static void main(String[] args) throws Exception{
		Thread.sleep(100000);
	}
}
