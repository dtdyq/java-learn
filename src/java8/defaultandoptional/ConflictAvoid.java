package java8.defaultandoptional;
/**
 * 
 * @author dtdyq
 * 
 * ʵ�ֶ���ӿ�ʱ���ֳ�ͻ�Ľ��������
 * 1��������������ķ�����Զ��Ĭ�Ϸ��������ȼ���
 * 2���ӽӿڵ����ȼ�����
 * 3������������������޷��жϣ�����������ʾ���Ǻ͵���ϣ��ʹ�õķ���
 *
 */
public class ConflictAvoid implements A,B{
	@Override
	public void hello() {
		A.super.hello();
	}
}
interface A{
	default void hello(){
		System.out.println("hello from A");
	}
}
interface B{
	default void hello(){
		System.out.println("hello from B");
	}
}
