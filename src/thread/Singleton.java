package thread;
/**
 * ����������(�̰߳�ȫ)��
 * @author dtdyq
 *
 */
/**
 * ����ʽ��
 * 1������˽�й�����
 * 2������˽�о�̬����
 * 3�������ṩ��ȡ��̬���Եķ���
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
 * ����ʽ��
 * 1������˽�й�����
 * 2������˽�о�̬���Բ���ʼ��
 * 3�������ṩ��ȡ��̬���Եķ���
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
 * ����Ч�Ķ���ʽ��
 * 1������˽�й�����
 * 2������˽���ಢ����˽�о�̬����
 * 3�������ṩ��ȡ��̬���Եķ���
 */
class Singleton2 {
	//˽���ಢ����̬�����ڳ�ʼ��ʱ�ͼ��أ������ڵ���ʱ�ż��أ��ӻ��˼���ʱ�䣬����Ч
	private static class SingHolder{
		private static  Singleton2 instance=new Singleton2();
	}
	
	private Singleton2(){
		
	}
	public static synchronized Singleton2 getInstance(){
		return SingHolder.instance;
	}
}




