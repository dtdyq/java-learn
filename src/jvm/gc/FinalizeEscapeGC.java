package jvm.gc;
/**
 * 
 * @author dtdyq
 * 
 * 可达性分析算法：
 * 通过称为“gc roots”的对象作为起始点，向下搜索，搜索过程所走过的路线被称为饮用链
 * 如果某个对象不在引用链上，即该对象不可达，则证明该对象不再能被引用
 * 
 * java中的引用类型：
 *     强引用(String reference):程序代码中普遍存在的引用
 *     软引用(soft reference): 软饮用描述一些有用但非必要的对象，对于被软饮用的对象
 *     只有在系统将要发生内存溢出时，才会被gc收集，如果内存依然不过，则抛出OOM
 *     弱引用(weak reference):比软引用更弱一些，被弱引用关联的对象只能存活到下一次
 *     gc之前
 *     虚引用(Phantom Reference):最弱的引用，此类引用对象已经不可使用，唯一的目的是
 *     在对象被gc收集时可以收到一个系统通知
 *     java分别有SoftReference、WeakReference、PhantomReference实现这几种引用
 * 
 * gc工作：
 * 如果对象在进行可达性分析时发现没有与GC roots相连接的引用链，那么该对象会被第一次
 * 标记，然后进行筛选，如果对象没有重写finalize方法或该方法已经被调用过，则没有必要执行
 * 如果对象被判定为需要执行finalize方法，则会被放在F-Queue队列中，在稍后的gc中，F-Queue
 * 中的对象被第二次标记，如果这期间对象没有通过finalize方法拯救自己，则会被彻底回收
 * 
 * 下面的程序演示了对象在finalize方法中使得自己重新引用对象而不被收集
 * 但在第二次执行相同的代码时，对象已经被收集，因为finalize方法只执行一次
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
		//对象第一次成功拯救自己：
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(5000);//finalize 方法优先级很低，主线程休息5秒等等她
		if (SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am died");
		}
		//对象第二次拯救自己失败
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
