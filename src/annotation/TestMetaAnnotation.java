package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java.lang.annnoation�������ṩ��6��Meta Annotationר�����ڴ���java8�������ظ�ע��
 * 
 * @ Retention��ֻ����������Annotation���壬����ָ�����ε�Annotation���Ա����೤ʱ��
 * �����ε�ֵ����������
 * 		RetentionPolicy.CLASS:��������Annotation��¼��class�ļ��У�������java����ʱ
 * 							  ���ɻ�ȡAnnotation������Ĭ��ֵ
 * 		RetentionPolicy.RUNTIME:��������Annotation������class�У�����java�ļ�ʱ������
 * 								ͨ�������ȡAnnotation��Ϣ
 * 		RetentionPolicy.SOURCE:Annotationֻ������Դ�����У�������ֱ�Ӷ���Annotation
 * 
 * 
 * @ Target:ֻ������Annotation���壬����ָ�������ε�Annotation������������Щ����Ԫ
 * ֵ�����¼��֣�
 * 		ElementType.ANNOTATION_TYPE ָ���ò��Ե�Annotationֻ������Annotation
 * 		ElementType.CONSTRUCTOR  ֻ�����ι�����
 * 		ElementType.FIELD   ֻ�����γ�Ա����
 * 		ElementType.LOCAL_VARIABLE   ֻ�����ξֲ�����
 * 		ElementType.METHOD   ֻ�����η���
 * 		ElementType.PACKAGE  ֻ�����ΰ�
 * 		ElementType.PARAMETER   ֻ�����β���
 * 		ElementType.TYPE   ���������ࡢ�ӿڻ�ö�ٶ���
 * 
 * 
 * @ Documented : ����ָ������Meta Annotation���ε�Annotation�ཫ�ᱻ��ȡ���ĵ�
 * 
 * 
 * @ Inherited�� ����Meta Annotation���ε�Annotation���������ĳ�����У�����������
 * �Զ�����Annotation����
 * @author dtdyq
 *
 */
public class TestMetaAnnotation {
	//��Annotation���������ɵ�doc�г���
	@Docable
	public void foo(){
		System.out.println("foo...");
	}
	public static void main(String[] args) {
		
	}
}

@Retention(RetentionPolicy.CLASS)
@interface Testable{}

@Target(ElementType.TYPE)
@interface targetable{}

@Documented
@interface Docable{}

@Inherited
@interface Inheritable{}

@Inheritable
class F{}
class C extends F{
	@org.junit.Test
	void Test(){
		//�������Ϊtrue��������F����ʹ��@ Inheritable����ʱ�������false
		System.out.println(C.class.isAnnotationPresent(Inheritable.class));
	}
}







