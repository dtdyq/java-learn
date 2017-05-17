package jvm.memory;


/**
 * @author dtdyq
 * java�����ջ����StackOverflowError�쳣��
 * -Xss������ջ�ڴ�����
 * -Xss128k
 *
 *��������
 *  stack length: 989  Exception in thread "main" 
	java.lang.StackOverflowError
		at jvm.JvmStackSOF.stackLeak(TestOOM.java:71)
		at jvm.JvmStackSOF.stackLeak(TestOOM.java:72)
		......
	
	���߳��£�������ջ̫֡����ջ�ڴ�̫С����ֻ�ᷢ��StackOverfowError�쳣

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
