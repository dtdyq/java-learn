package io;

import java.io.*;
/**
 * @author dtdyq
 *	ObjectOutputStream  ObjectInputStream  对象的输入输出：
 *	如果希望对象能够被写入IO流，则必须实现序列化
 *	如果一个类要实现序列化，则它的所有引用类型也要实现序列化
 *		Serializable  Externalizable
 *
 *	Externallizable 是用于让用户自己定义序列化和反序列化的接口
 *	而且是强制性的
 *		即必须实现readExternal()、writeExternal();
 *	实现Externalizable的类必须提供无参构造器
 *		用于在反序列化时，程序调用该类的无参构造器
 *	Externalizable的性能比Serializable的性能略好
 *
 *
 * 	为了解决版本问题，应该为每个实现序列化的类提供
 * 		private final static long serialVersionUID
 * 	只要该值相同，就能保证版本的一致性
 */
public class TestObjectStream {
	public static void main(String[] args) throws ClassNotFoundException{
		ObjectOutputStream obj=null;
		Dog dog=new Dog(12,23.34,"iii");
		ObjectInputStream oij=null;
		try{
			obj=new ObjectOutputStream(new FileOutputStream("file/io/Object.txt"));
			obj.writeObject(dog);
			oij=new ObjectInputStream(new FileInputStream("file/io/Object.txt"));
			Dog d=(Dog)oij.readObject();
			System.out.println(d);
		}catch(IOException e){
			System.out.println(e);
		}finally{
			try{
				obj.close();
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
}
//所有能被写入流中的对象都必须实现序列化、
//如果一个对象持有另一个对象的引用，则另一个对象也必须实现序列化，否则该对象也不能被序列化 
class Dog implements Serializable{
	private static final long serialVersionUID = 87645L;
	private int age;
	//transient修饰的不能被序列化
	transient double weight;
	static String name="fuddv";
	public Dog(int age,double weight,String name){
		this.age=age;
		this.weight=weight;
		Dog.name=name;
	}
	public String toString(){
		return age+" "+weight+" "+name;
	}

}