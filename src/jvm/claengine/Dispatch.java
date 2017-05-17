package jvm.claengine;

/**
 * 
 * @author dtdyq
 * 
 *         java中的单分派和多分派
 *
 */
public class Dispatch {
	static class QQ {}

	static class _360 {}

	public static class Father {
		public void hardChoice(QQ arg) {
			System.out.println("father choose qq");
		}

		public void hardChoice(_360 arg) {
			System.out.println("father choose 360");
		}
	}

	public static class Son extends Father {
		@Override
		public void hardChoice(_360 arg) {
			System.out.println("son choose 360");
		}

		@Override
		public void hardChoice(QQ arg) {
			System.out.println("son choose qq");
		}
	}

	public static void main(String[] args) {
		Father f = new Father();
		Father son = new Son();
		f.hardChoice(new _360());
		son.hardChoice(new QQ());
	}
}
