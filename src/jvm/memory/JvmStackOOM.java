package jvm.memory;

/**
 * @author dtdyq
 * java�����ջ����OutOfMemoryError�쳣��
 * ֻ���ڶ��̵߳�����²��ܳ���OOM�쳣
 * ��Ϊ��������ܷ�����ڴ����ޣ���ȥ�������Ͷ��ڴ棬ʣ�µļ��������ջ����ʹ�õ�
 * �ڴ��С�����̹߳���ʱ���ڴ�ܿ첻��Ӷ�����OOM�쳣
 * 
 * ע�⣺����-Xss��Ϊÿ���̷߳�����ڴ��С�������������ջ��OOM�쳣ʱ������߳���
 * ���ܼ��٣���Ӧ�ü�С�ò���ֵ��ʹ��ÿ���߳�ռ�е�ջ�ڴ��С���Ӷ����ɸ�����߳�
 * 
 * -Xss2m
 */
public class JvmStackOOM{
	void dontStop(){
		while(true){
			
		}
	}
	public static void main(String[] args){
		while(true){
			new Thread(()->{
			    new JvmStackOOM().dontStop();
			}
		    ).start();
		}
	}
	
}
