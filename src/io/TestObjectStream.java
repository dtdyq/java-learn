package io;

import java.io.*;
/**
 * @author dtdyq
 *	ObjectOutputStream  ObjectInputStream  ��������������
 *	���ϣ�������ܹ���д��IO���������ʵ�����л�
 *	���һ����Ҫʵ�����л���������������������ҲҪʵ�����л�
 *		Serializable  Externalizable
 *
 *	Externallizable ���������û��Լ��������л��ͷ����л��Ľӿ�
 *	������ǿ���Ե�
 *		������ʵ��readExternal()��writeExternal();
 *	ʵ��Externalizable��������ṩ�޲ι�����
 *		�����ڷ����л�ʱ��������ø�����޲ι�����
 *	Externalizable�����ܱ�Serializable�������Ժ�
 *
 *
 * 	Ϊ�˽���汾���⣬Ӧ��Ϊÿ��ʵ�����л������ṩ
 * 		private final static long serialVersionUID
 * 	ֻҪ��ֵ��ͬ�����ܱ�֤�汾��һ����
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
//�����ܱ�д�����еĶ��󶼱���ʵ�����л���
//���һ�����������һ����������ã�����һ������Ҳ����ʵ�����л�������ö���Ҳ���ܱ����л� 
class Dog implements Serializable{
	private static final long serialVersionUID = 87645L;
	private int age;
	//transient���εĲ��ܱ����л�
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