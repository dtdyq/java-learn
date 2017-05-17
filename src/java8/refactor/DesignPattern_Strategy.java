package java8.refactor;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 代码重构之使用lambda实现面向对象的设计模式
 * 
 * 策略模式：包含三部分内容：
 * 		一个代表某个算法的接口
 * 		一个或多个该接口的实现，它们代表了该算法的多种实现
 * 		一个或多个使用策略对象的客户
 *eg：验证输入的内容是否根据标准进行了格式化:
 */
public class DesignPattern_Strategy {
	@Test
	public void testNormal(){
		//使用普通的方法实现：
		Validator v = new Validator(new IsNumeric());
		System.out.println(v.execute("375483"));
	}
	@Test
	public void testLambda(){
		//使用lambda实现同样的检验：
		Validator v = new Validator(e->e.matches("[A-Z]+"));
		System.out.println(v.execute("IBEWCYTWEC"));
	}
}
interface ValidationStrategy{
	boolean execute(String s);
}
//对接口的多个实现：
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
//新建类来使用这些验证策略：
class Validator{
	private final ValidationStrategy strategy;
	public Validator(ValidationStrategy strategy){
		this.strategy = strategy;
	}
	public boolean execute(String s){
		return strategy.execute(s);
	}
}














