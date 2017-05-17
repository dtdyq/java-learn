package jvm.claengine;

/**
 * 
 * @author dtdyq
 * 
 *         ∑Ω∑®æ≤Ã¨∑÷≈…
 *
 */
public class StaticDispatch {
	

	public void setHello(Human h) {
		System.out.println("hello human");
	}

	public void setHello(Man m) {
		System.out.println("hello man");
	}

	public void setHello(Woman w) {
		System.out.println("hello woman");
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		StaticDispatch sd = new StaticDispatch();
		sd.setHello(man);
		sd.setHello(woman);
	}
}
abstract class Human {

}

class Man extends Human {

}

class Woman extends Human {

}
