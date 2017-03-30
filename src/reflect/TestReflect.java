package reflect;

import java.lang.reflect.*;

public class TestReflect {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args)
			throws ClassNotFoundException, Exception, Exception{
		
		Class c= Class.forName("reflect.Person");
		System.out.println(c);
		Constructor[] cs=c.getConstructors();
		for(Constructor cc:cs){
			System.out.println(cc);
		}
		
		Constructor cns=c.getDeclaredConstructor(String.class,int.class);
		Object o=cns.newInstance("kkk",99);
		System.out.println(o);
		Person p=(Person) c.newInstance();
		System.out.println(p);
		System.out.println("-------------------------------");
		Field[] f=c.getDeclaredFields();
		for(Field ff:f){
			System.out.println(ff);
		}
		Field fn=c.getDeclaredField("name");
		fn.setAccessible(true);
		fn.set(p, "test");
		System.out.println(p);
		System.out.println("-------------------------");
		Method[] m=c.getDeclaredMethods();//获取自己的方法
		Method[] ms=c.getMethods();//获取所有方法
		
		for(Method mm:m){
			System.out.println(mm);
		}
		System.out.println();
		for(Method mmm:ms){
			System.out.println(mmm);
		}
		Method mshow=c.getMethod("show", null);
		mshow.setAccessible(true);
		mshow.invoke(new Person(), null);
		Method mmh=c.getDeclaredMethod("Method", String.class);
		mmh.setAccessible(true);
		mmh.invoke(p, "hello");
		Method mg=c.getDeclaredMethod("get", String.class);
		mg.setAccessible(true);
		System.out.println("-->"+mg.invoke(p, " person"));
	}
}
class Person{
	private String name;
	public int age;
	String address;
	public Person(){
		
	}
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
	Person(String name,int age,String address){
		this.name=name;
		this.age=age;
		this.address=address;
	}
	public void show(){
		System.out.println("show");
	}
	void Method(String na){
		System.out.println("Method "+na);
	}
	private String get(String s){
		return "get "+s;
	}
	public String toString(){
		return name+":"+age+":"+address;
	}
}

