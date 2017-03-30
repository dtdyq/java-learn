package reflect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * reflect包下有Proxy类和InvocationHandler接口，通过该类和接口可以生成JDK
 * 的动态代理类或动态代理对象
 * @author dtdyq
 *
 */
public class TestInvok {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		Pers p=new Persons();
		Pers pp=(Pers)MyProxyFactory.getProxy(p);
		pp.show();
		pp.Method("testt");
		
	}
}
//为指定的类生成动态代理实例：
class MyProxyFactory{
	public static Object getProxy(Object target){
		InvocationHandler ih=new MyInvo(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), ih);
		
	}
}
class MyInvo implements InvocationHandler{
	private Object obj;
	public MyInvo(Object obj){
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("---test---");
		Object o=method.invoke(obj, args);
		System.out.println("---over---");
		return o;
	}
	
}
interface Pers{
	public void show();
	public void Method(String args);
}
class Persons implements Pers{
	private String name;
	public String add;
	public String toString(){
		return name+"--->"+add;
	}
	public void show(){
		System.out.println("show");
	}
	public void Method(String args){
		System.out.println("hello=-->"+args);
	}
}