package thread;
/**
 * 创建单例类(线程安全)：
 * @author dtdyq
 *
 */
/**
 * 懒汉式：
 * 1、创建私有构造器
 * 2、声明私有静态属性
 * 3、对外提供获取静态属性的方法
 */
public class Singleton {
	private static Singleton instance=null;
	private Singleton(){
		
	}
	public static Singleton getInstance(){
		if(null==instance){
			synchronized(Singleton.class){
				if(null==instance){
					instance=new Singleton();
				}
			}
		}
		return instance;
	}
	public static void main(String[] args){
		
	}
}
/**
 * 饿汉式：
 * 1、创建私有构造器
 * 2、声明私有静态属性并初始化
 * 3、对外提供获取静态属性的方法
 */
class Singleton1 {
	private static Singleton1 instance=new Singleton1();
	private Singleton1(){
		
	}
	public static synchronized Singleton1 getInstance(){
		return instance;
	}
}
/**
 * 更高效的饿汉式：
 * 1、创建私有构造器
 * 2、创建私有类并声明私有静态属性
 * 3、对外提供获取静态属性的方法
 */
class Singleton2 {
	//私有类并不像静态变量在初始化时就加载，而是在调用时才加载，延缓了加载时间，更高效
	private static class SingHolder{
		private static  Singleton2 instance=new Singleton2();
	}
	
	private Singleton2(){
		
	}
	public static synchronized Singleton2 getInstance(){
		return SingHolder.instance;
	}
}




