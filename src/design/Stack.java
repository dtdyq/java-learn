package design;

import java.util.Arrays;

/**
 * 
 * @author dtdyq
 * 
 * �������ڶ��������:���¶�����ж����޷�����
 *
 */
public class Stack {
	private Object[] elements;
	private int size;
	private final static int DEFAULT_CAPACITY = 15;
	public Stack(){
		elements = new Object[DEFAULT_CAPACITY];
	}
	public void push(Object o){
		ensureCapacity();
		elements[size++]=o;
	}
	public Object pop() throws Exception{
		if(size==0){
			throw new Exception();
		}
		return elements[--size];
	}
	//����������
	public Object popSafed() throws Exception{
		if(size==0){
			throw new Exception();
		}
		Object res = elements[--size];
		elements[size] = null;
		return res;
	} 
	private void ensureCapacity() {
		if(elements.length==size){
			elements = Arrays.copyOf(elements, 2*size+1);
		}
	}
}











