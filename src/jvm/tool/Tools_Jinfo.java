package jvm.tool;
/**
 * 
 * @author dtdyq
 * 
 * Jinfo：实时地查看和调整虚拟机各项参数，使用jps命令的-v参数可以查看虚拟机启动时显式指定
 *        的参数列表
 *        
 * 如果想知道未被显式指定的参数的系统默认值，可以使用jinfo的-flag选项进行查询
 * 
 * jinfo还可以使用-sysprops选项，把虚拟机进程的System.getProperties()的内容打印出来
 * 
 * -flag [+/-]name 或者 -flag name=value用于修改一部分运行期可写的虚拟机参数值
 * 
 **/
public class Tools_Jinfo {
	
}
