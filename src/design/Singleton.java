package design;

import java.io.Serializable;

/**
 * 
 * @author dtdyq
 * 
 * ʵ�ֵ�����
 *
 */
public class Singleton implements Serializable{
	private static final long serialVersionUID = 1L;
	private Singleton(){}
	public final static Singleton sing = new Singleton();
	public static Singleton getInstance(){
		return sing;
	}
	//��ֹ���л�ʱ�����ǵ���ʵ��
	private Object readResolve(){
		return sing;
	}
}

