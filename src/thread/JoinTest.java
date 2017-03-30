package thread;

/**
 * join: һ���̵߳ȴ���һ���߳���ɵķ���������ĳ������ִ�����е��������̵߳�
 * join����ʱ�����߳̽��ᱻ������ֱ����join������߳�ִ����Ϊֹ
 */
public class JoinTest implements Runnable{

	public static void main(String[] args) throws InterruptedException {
		JoinTest jt=new JoinTest();
		Thread t=new Thread(jt);
		t.start();
		for(int i=0;i<100;i++){
			Thread.sleep(10);
			if(i==40){
				t.join();   //main�̱߳�����
			}
			System.out.println("main--->"+i);
		}
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			try {
				Thread.sleep(10);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("join--->"+i);
		}
		
	}

}
