package reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * ���÷�����Ϊ�������������������Ԫ��
 * @author dtdyq
 *
 */
public class TestGenericArray {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, Exception, Exception{
		ArrayList<Integer> array=new ArrayList<>();
		array.add(543);
//		array.add("hello");
		@SuppressWarnings("rawtypes")
		Class c=array.getClass();
		@SuppressWarnings("unchecked")
		Method m=c.getMethod("add",Object.class);
		m.invoke(array,"hello");
		System.out.println(array);
	}
}
