package java8.refactor;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * �����ع�֮ʹ��lambdaʵ�������������ģʽ
 * 
 * ������ģʽ��һ�����񱻴��ݸ�һ������������Ľ���ٽ�����һ������
 *
 */
public class DesignPattern_ResponsibilityChain {
	@Test
	public void test(){
		ProcessObj<String> pHead = new HeadTextProcess();
		ProcessObj<String> pTail = new TailTextProcess();
		
		pHead.setProcessor(pTail);
		System.out.println(pHead.handle("hello"));
		
		//ʹ��lambda���ʽ��
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
//���������ĳ����ࣺ
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
//�����������
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










