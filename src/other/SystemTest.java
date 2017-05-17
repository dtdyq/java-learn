package other;

/**
 * @author dtdyq
 * System的identityHashCode()生成哈希值
 */
public class SystemTest{
	public static void main(String[] args) {
		String s1=new String("Test");
		String s2=new String("Test");
		System.out.println("s1-->"+s1.hashCode());
		System.out.println("s2-->"+s2.hashCode());
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		String s11="Test";
		String s22="Test";
		System.out.println(s11.hashCode());
		System.out.println(s22.hashCode());
		System.out.println(System.identityHashCode(s11));
		System.out.println(System.identityHashCode(s22));
	}
}
