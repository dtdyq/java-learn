package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * �ɴ��Է����㷨��
 * ͨ����Ϊ��gc roots���Ķ�����Ϊ��ʼ�㣬���������������������߹���·�߱���Ϊ������
 * ���ĳ���������������ϣ����ö��󲻿ɴ��֤���ö������ܱ�����
 * 
 * java�е��������ͣ�
 *     ǿ����(String reference):����������ձ���ڵ�����
 *     ������(soft reference): ����������һЩ���õ��Ǳ�Ҫ�Ķ��󣬶��ڱ������õĶ���
 *     ֻ����ϵͳ��Ҫ�����ڴ����ʱ���Żᱻgc�ռ�������ڴ���Ȼ���������׳�OOM
 *     ������(weak reference):�������ø���һЩ���������ù����Ķ���ֻ�ܴ���һ��
 *     gc֮ǰ
 *     ������(Phantom Reference):���������ã��������ö����Ѿ�����ʹ�ã�Ψһ��Ŀ����
 *     �ڶ���gc�ռ�ʱ�����յ�һ��ϵͳ֪ͨ
 *     java�ֱ���SoftReference��WeakReference��PhantomReferenceʵ���⼸������
 * 
 * gc������
 * ��������ڽ��пɴ��Է���ʱ����û����GC roots�����ӵ�����������ô�ö���ᱻ��һ��
 * ��ǣ�Ȼ�����ɸѡ���������û����дfinalize������÷����Ѿ������ù�����û�б�Ҫִ��
 * ��������ж�Ϊ��Ҫִ��finalize��������ᱻ����F-Queue�����У����Ժ��gc�У�F-Queue
 * �еĶ��󱻵ڶ��α�ǣ�������ڼ����û��ͨ��finalize���������Լ�����ᱻ���׻���
 * 
 * ����ĳ�����ʾ�˶�����finalize������ʹ���Լ��������ö���������ռ�
 * ���ڵڶ���ִ����ͬ�Ĵ���ʱ�������Ѿ����ռ�����Ϊfinalize����ִֻ��һ��
 * 
 *
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK=null;
	public void isAlive(){
		System.out.println("yes,i am still alive");
	}
	@Override
	protected void finalize()throws Throwable{
		super.finalize();
		System.out.println("finalize method executed!");
		FinalizeEscapeGC.SAVE_HOOK=this;
	}

	public static void main(String[] args) throws Exception{
		SAVE_HOOK = new FinalizeEscapeGC();
		//�����һ�γɹ������Լ���
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(5000);//finalize �������ȼ��ܵͣ����߳���Ϣ5��ȵ���
		if (SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am died");
		}
		//����ڶ��������Լ�ʧ��
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(5000);
		if (SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am died");
		}
		
	}
}
