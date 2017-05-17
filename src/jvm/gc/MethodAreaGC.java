package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * 方法区的常量和类也需要被回收
 * 对字符串的回收要看该字符串是否被引用，如果没有，则可以直接清理出常量池
 * 对于类的收集，首先要判断类是否无用：
 * 	判断类无用的条件：
 * 		堆中不存在该类的任何实例
 * 		加载该类的ClassLoader已经被回收
 * 		该类对应的Class对象没有在任何地方被引用，即无法通过反射访问该类的方法
 * 
 * 是否对类进行回收，可以用参数-Xnoclassgc指定
 * 
 * 可以使用-verboss:class
 * 		  -XX:+TraceClassLoading
 * 		  -XX:+TraceClassUnLoading
 *     查看类的加载和卸载信息
 * 
 * 
 */
public class MethodAreaGC {

}
