package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
/**
 * java8新增的方法参数反射：
 * @author dtdyq
 *
 */
public class TestMethod {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class<TestM> cla=TestM.class;
		Method rep=cla.getMethod("replace", String.class,int.class,List.class);
		System.out.println("replace's parameters: "+rep.getParameterTypes());
		Parameter[] param=rep.getParameters();
		System.out.println(param);
		
		int count=1;
		for(Parameter p:param){
//			if(p.isNamePresent()){
				System.out.println("----"+(count++)+" parameter----");
				//获取形参名：
				System.out.println(p.getName());
				//获取形参类型：
				System.out.println(p.getType());
				//获取泛型类型：
				System.out.println(p.getParameterizedType());
//			}
		}
	}
}

class TestM{
	public static void replace(String str,int i,List<String> list){};
}
