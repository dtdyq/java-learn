package annotation;

import java.lang.annotation.Annotation;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ��ȡannotation�����ͺ�Ԫ����
 *
 */
public class GetAnnotation {
	@Test
	public void test() throws Exception{
		//��ȡ����ע�⣺
		Annotation[] ann = 
				Class.forName("annotation.DefineAnnotation")
				.getMethod("methods").getAnnotations();
		for(Annotation a:ann){
			System.out.println(a);
		}
	}
	@Test
	public void test2() throws Exception{
		//��ȡע��Ԫ��Ϣ
		Annotation[] ann = 
				Class.forName("annotation.DefineAnnotation")
				.getMethod("methods").getAnnotations();
		for(Annotation a:ann){
			System.out.println(a);
			System.out.println(((myTag)a).name());
			System.out.println(((myTag)a).age());
			
		}
	}
}
