package jvm.claengine;
/**
 * 
 * @author dtdyq
 *
 */
public class DynamicDispatch {
	static abstract class Human{
		public void sayHello(){
			System.out.println("hello human");
		}
	}
	static class Man extends Human{
		public void sayHello(){
			System.out.println("hello man");
		}
	}
	static class Woman extends Human{
		public void sayHello(){
			System.out.println("hello woman");
		}
	}
	public static void say(Human hu){
		hu.sayHello();
	}
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		DynamicDispatch.say(man);
		DynamicDispatch.say(woman);
		man = new Woman();
		DynamicDispatch.say(man);
	}
}










