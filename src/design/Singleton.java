package design;

import java.io.Serializable;

/**
 * 
 * @author dtdyq
 * 
 * 实现单例类
 *
 */
public class Singleton implements Serializable{
	private static final long serialVersionUID = 1L;
	private Singleton(){}
	public final static Singleton sing = new Singleton();
	public static Singleton getInstance(){
		return sing;
	}
	//防止序列化时创建非单例实例
	private Object readResolve(){
		return sing;
	}
}

