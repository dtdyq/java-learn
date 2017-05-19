package annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * java8:可以使用重複註解
 *
 */
public class NewAnnotation {
	@ReptableTag(name="haha",age=32)
	@ReptableTag(name="rept",age=01)
	@Test
	public void testReptable() throws Exception{
		Class<NewAnnotation> clazz = NewAnnotation.class;
		Annotation[] ant = clazz.getMethod("testReptable").getAnnotations();
		for(Annotation a:ant){
			System.out.println(a);
		}
		//使用java8获取多个注解：
		ReptableTag[] tag = clazz.
				getMethod("testReptable").
				getDeclaredAnnotationsByType(ReptableTag.class);
		for(ReptableTag t :tag){
			System.out.println(t);
		}
	}

}
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ReptableTags.class)
@interface ReptableTag{
	String name();
	int age();
}
@Retention(RetentionPolicy.RUNTIME)
@interface ReptableTags{
	ReptableTag[] value();
}