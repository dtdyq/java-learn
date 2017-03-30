package thread;

public class SynClass {

	public static void main(String[] args) throws InterruptedException {
		Jvm j1=Jvm.getInstance(1);
		Jvm j2=Jvm.getInstance(2);
		System.out.println(j1);
		System.out.println(j2);
		JvmThread t1=new JvmThread(200);
		JvmThread t2=new JvmThread(1000);
		t1.start();
		t2.start();

	}

}
class JvmThread extends Thread{
	private int time;
	public JvmThread(int time){
		this.time=time;
	}
	public void run(){
		try {
			System.out.println(Thread.currentThread()+"-->"+Jvm.getInstance3(time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
//�������ģʽ
//����ʽ��
class Jvm{
	private static Jvm instance=null;
	private Jvm(){
		
	}
	public static Jvm getInstance(int time) throws InterruptedException{
		if(null==instance){
			Thread.sleep(time);
			instance=new Jvm();
		}
		return  instance;
	}
	//�̰߳�ȫ��
	public static synchronized Jvm getInstance1(int time) throws InterruptedException{
		if(null==instance){
			Thread.sleep(time);
			instance=new Jvm();
		}
		return  instance;
	}
	//�̰߳�ȫ��
	public static Jvm getInstance2(int time) throws InterruptedException{
		synchronized(Jvm.class){
			if(null==instance){
				Thread.sleep(time);
				instance=new Jvm();
			}
			return  instance;
		}
	}
	//����Ч���̰߳�ȫ��
	//˫�ؼ�飺double checking:
	public static Jvm getInstance3(int time) throws InterruptedException{
		if(null==instance){
			synchronized(Jvm.class){
				if(null==instance){
					Thread.sleep(time);
					instance=new Jvm();
				}
			}
		}
		return  instance;
	}
}