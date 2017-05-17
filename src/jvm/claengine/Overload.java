package jvm.claengine;

/**
 * 
 * @author dtdyq
 * 
 * 重载方法匹配优先级
 *
 */
public class Overload {
	public void say(Object o) {
		System.out.println("Object");
	}

	public void say(char... o) {
		System.out.println("char array");
	}

	public void say(Long o) {
		System.out.println("long");
	}

	public void say(int o) {
		System.out.println("intege");
	}

	public void say(Character o) {
		System.out.println("character");
	}

	public void say(char o) {
		System.out.println("char");
	}

	public static void main(String[] args) {
		Overload al = new Overload();
		al.say('s');
		
	}
}
