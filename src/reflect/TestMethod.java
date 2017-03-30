package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
/**
 * java8�����ķ����������䣺
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
				//��ȡ�β�����
				System.out.println(p.getName());
				//��ȡ�β����ͣ�
				System.out.println(p.getType());
				//��ȡ�������ͣ�
				System.out.println(p.getParameterizedType());
//			}
		}
	}
}

class TestM{
	public static void replace(String str,int i,List<String> list){};
}
