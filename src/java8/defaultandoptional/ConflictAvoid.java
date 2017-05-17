package java8.defaultandoptional;
/**
 * 
 * @author dtdyq
 * 
 * 实现多个接口时名字冲突的解决方法：
 * 1、类或父类中声明的方法永远比默认方法的优先级高
 * 2、子接口的优先级更高
 * 3、上述两条规则如果无法判断，则该类必须显示覆盖和调用希望使用的方法
 *
 */
public class ConflictAvoid implements A,B{
	@Override
	public void hello() {
		A.super.hello();
	}
}
interface A{
	default void hello(){
		System.out.println("hello from A");
	}
}
interface B{
	default void hello(){
		System.out.println("hello from B");
	}
}
