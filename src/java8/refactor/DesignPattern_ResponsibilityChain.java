package java8.refactor;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 代码重构之使用lambda实现面向对象的设计模式
 * 
 * 责任链模式：一个任务被传递给一个对象处理，处理的结果再交给另一个对象
 *
 */
public class DesignPattern_ResponsibilityChain {
	@Test
	public void test(){
		ProcessObj<String> pHead = new HeadTextProcess();
		ProcessObj<String> pTail = new TailTextProcess();
		
		pHead.setProcessor(pTail);
		System.out.println(pHead.handle("hello"));
		
		//使用lambda表达式：
		UnaryOperator<String> head = text->{
			return "[head of String] "+text;
		};
		UnaryOperator<String> tail = text->{
			return text+" [tail of String]";
		};
		Function<String,String> pro = head.andThen(tail);
		System.out.println(pro.apply("middle"));
	}

}
//代表处理对象的抽象类：
abstract class ProcessObj<T>{
	protected ProcessObj<T> successor;
	public void setProcessor(ProcessObj<T> successor){
		this.successor = successor;
	}
	public T handle(T input){
		T r = handleWork(input);
		if(successor != null){
			return successor.handle(r);
		}
		return r;
	}
	abstract protected T handleWork(T input);
}
//创建处理对象：
class HeadTextProcess extends ProcessObj<String>{
	@Override
	protected String handleWork(String input) {
		return "[this is the head] "+input;
	}
}
class TailTextProcess extends ProcessObj<String>{
	@Override
	protected String handleWork(String input) {
		return input+" [this is the end]";
	}
}










