package jvm.memory;


/**
 * @author dtdyq
 * java虚拟机栈出现StackOverflowError异常，
 * -Xss：设置栈内存容量
 * -Xss128k
 *
 *输出结果：
 *  stack length: 989  Exception in thread "main" 
	java.lang.StackOverflowError
		at jvm.JvmStackSOF.stackLeak(TestOOM.java:71)
		at jvm.JvmStackSOF.stackLeak(TestOOM.java:72)
		......
	
	单线程下，无论是栈帧太大还是栈内存太小，都只会发生StackOverfowError异常

 */
public class JvmStackSOF{
	int stackLength = 1;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	public static void main(String[] args){
		JvmStackSOF sof = new JvmStackSOF();
		try{
			sof.stackLeak();
		}catch(Throwable e){
			System.out.println("stack length: "+sof.stackLength);
			throw e;
		}
	}
}
