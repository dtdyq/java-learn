package annotation;

import java.lang.annotation.Annotation;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 获取annotation的类型和元数据
 *
 */
public class GetAnnotation {
	@Test
	public void test() throws Exception{
		//获取所有注解：
		Annotation[] ann = 
				Class.forName("annotation.DefineAnnotation")
				.getMethod("methods").getAnnotations();
		for(Annotation a:ann){
			System.out.println(a);
		}
	}
	@Test
	public void test2() throws Exception{
		//获取注解元信息
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
