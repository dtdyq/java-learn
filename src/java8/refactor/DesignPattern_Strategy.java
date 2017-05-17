package java8.refactor;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * �����ع�֮ʹ��lambdaʵ�������������ģʽ
 * 
 * ����ģʽ���������������ݣ�
 * 		һ������ĳ���㷨�Ľӿ�
 * 		һ�������ýӿڵ�ʵ�֣����Ǵ����˸��㷨�Ķ���ʵ��
 * 		һ������ʹ�ò��Զ���Ŀͻ�
 *eg����֤����������Ƿ���ݱ�׼�����˸�ʽ��:
 */
public class DesignPattern_Strategy {
	@Test
	public void testNormal(){
		//ʹ����ͨ�ķ���ʵ�֣�
		Validator v = new Validator(new IsNumeric());
		System.out.println(v.execute("375483"));
	}
	@Test
	public void testLambda(){
		//ʹ��lambdaʵ��ͬ���ļ��飺
		Validator v = new Validator(e->e.matches("[A-Z]+"));
		System.out.println(v.execute("IBEWCYTWEC"));
	}
}
interface ValidationStrategy{
	boolean execute(String s);
}
//�ԽӿڵĶ��ʵ�֣�
class IsAllLowerCase implements ValidationStrategy{
	@Override
	public boolean execute(String s) {
		return s.matches("[a-z]+");
	}
}
class IsNumeric implements ValidationStrategy{
	@Override
	public boolean execute(String s) {
		return s.matches("\\d+");
	}
}
//�½�����ʹ����Щ��֤���ԣ�
class Validator{
	private final ValidationStrategy strategy;
	public Validator(ValidationStrategy strategy){
		this.strategy = strategy;
	}
	public boolean execute(String s){
		return strategy.execute(s);
	}
}














