package jvm.memory;

import java.lang.reflect.Method;

import jvm.memory.HeapOOM.OOMObject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author dtdyq
 * 
 * java������OOM�쳣��
 * ���������ڴ��class�������Ϣ�����������������η��������ء��ֶ������ͷ�������
 * 
 * ��ͨ������ʱ��������������������������Ҳ���Խ���CGLibֱ�Ӳ����ֽ���ʹ����ʱ����
 * ��������
 * 
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at jvm.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:39)

 *
 *�ɽ�����Կ�������ʾ����Java heap space����OOM�쳣
 *java8���Ѿ�û�����ô���һ����������ڸ��������ɴ��ڣ�����������Ԫ��Ϣ
 *�Ѿ���������ֱ�Ӵ��ڴ��������򣬶���ľ�̬�������ַ����ȷ����ڶ���
 *
 */
public class JavaMethodAreaOOM {
	public static void main(String[] args){
		while(true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor(){

				@Override
				public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
					
					return arg3.invoke(arg0,arg2);
				}
				
			});
			enhancer.create();
		}
	}

}
