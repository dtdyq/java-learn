package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author dtdyq
 * 
 * 自定义annotation
 *
 */

public class DefineAnnotation {
	@Tests
	@myTag(name="name",age=12)
	@defTag(age=11)
	public void methods(){
		
	}

}
//定义简单的annotation：
@interface Tests{
	
}
//带成员变量的annotation：
@Retention(RetentionPolicy.RUNTIME)
@interface myTag{
	//定义了两个成员变量：以方法的形式进行定义
	String name();
	int age();
}
//定义带有默认值成员变量的标签：
@interface defTag{
	String name() default "defName";
	int age();
}




