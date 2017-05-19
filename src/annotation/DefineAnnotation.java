package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author dtdyq
 * 
 * �Զ���annotation
 *
 */

public class DefineAnnotation {
	@Tests
	@myTag(name="name",age=12)
	@defTag(age=11)
	public void methods(){
		
	}

}
//����򵥵�annotation��
@interface Tests{
	
}
//����Ա������annotation��
@Retention(RetentionPolicy.RUNTIME)
@interface myTag{
	//������������Ա�������Է�������ʽ���ж���
	String name();
	int age();
}
//�������Ĭ��ֵ��Ա�����ı�ǩ��
@interface defTag{
	String name() default "defName";
	int age();
}




