package jvm.claengine;
/**
 * 
 * @author dtdyq
 * 
 * jvmִ�е�class�ļ��ṹ��
 * 		�޷������������������ͣ���u1��u2��u4��u8�ֱ��ʾ1��2��4��8���ֽڣ���������
 * 				���֡��������á�����ֵ���ַ���ֵ
 * 		������޷�����������������ɵĸ������ݽṹ
 ************************************************************************
 *magic number��ħ����class�ļ���ͷ�ĸ��ֽڣ�����ȷ������ļ��Ƿ����ܱ�jvm���ܵ�
 *class�ļ���CAFEBABE
 *
 *���������ĸ��ֽ���class�ļ��İ汾�ţ�����5��6�ֽ��Ǵΰ汾�ţ�7��8�ֽ������汾�ţ�
 *java�İ汾�Ŵ�45��ʼ��jdk1.1֮��ÿ��jdk��汾�����������汾���ϼ�һ��00000034
 *��ʾ�ΰ汾��Ϊ0�����汾��Ϊ0034����1.8
 *
 *���������ΰ汾��֮����ǳ�������ڣ������ؿ������ΪClass�ļ�֮�е���Դ�ֿ⣬������
 *��ڴ�����һ��u2���͵����ݣ�����������������ֵ����1��ʼ���������ʮ�������ֽ����
 *0016��ʮ���Ƶ�22����ʾ����������21���������������Ҫ���ڴ��������(Literal)��
 *��������(Symbolic Reference)�����������ı��ַ���������Ϊfinal�ĳ��������������ð���
 *��ͽӿڵ�Ȩ�޶������ֶε����ƺ������������������ƺ���������
 *	�������е�ÿһ�������һ��������ʾʮ���ֲ�ͬ�ı�ṹ���ݣ�ÿ����ĵ�һλ��һ��u1
 *���͵ı�־λ��������������������ֳ�������
 *
 *�����ų�����֮��������ֽڴ�����ʱ�־������ʶ��һЩ����߽ӿڲ�εķ�����Ϣ����������
 *class�ļ��������ǽӿڣ��Ƿ���Ϊpublic���ͣ��Ƿ���Ϊabstract���ͣ��Ƿ�����Ϊ
 *final
 *
 *���ʱ�־֮�����������������������ӿ��������ϣ��ֱ�Ϊu2��u2��һ��u2���͵ļ��ϣ���Щ����
 *ָ��CONSTANT_Utf8_info���͵ĳ������ó��������������ӿڵ�ȫ�޶�����
 *		   #1 = Class              #2             // jvm/clazz/TestClass
		   #2 = Utf8               jvm/clazz/TestClass
		   #3 = Class              #4             // java/lang/Object
		   #4 = Utf8               java/lang/Object
 *���ڽӿڣ���һ��u2�����ֶα�ʾ��ʵ�ֵĽӿ������������ÿ��u2ָ���Ӧ�ӿڵ�ȫ�޶���
 *
 *field_info:�ֶα����������ӿڻ������������ı������ֶΰ����༶������ʵ������������
 *���������ڲ������ľֲ�����
 *ÿ���ֶ���access_flag��name_index��descripter_index��ɣ��ֱ����������ֶ����η���
 *�ֶεļ����ƺ��ֶκͷ��������������������������������ֶε��������͡������Ĳ����б�
 *�ͷ���ֵ
 *
 *�������ϻ�����ʽ���ֶα���ͬ
 *
 *���Ա���������ĳЩ����ר�е���Ϣ
 *
 */
public class TestClass {
	class T{
		
	}
	private int m;
	public int inc(){
		return m+1;
	}
	public int inexp(){
		int x;
		try {
			x = 1;
			return x;
		} catch(Exception e){
			x = 2;
			return x;
		} finally{
			x = 3;
		}
	}
}
/**
 * ��������class�ļ�ʮ��������Ϣ��
 * 
 * CAFEBABE 00000034 0016 0700020100136A766D2F636C617A7A2F54657374436C617373070
 * 0040100106A6176612F6C616E672F4F626A6563740100016D010001490100063C696E6974
 * 3E010003282956010004436F64650A0003000B0C0007000801000F4C696E654E756D62657
 * 25461626C650100124C6F63616C5661726961626C655461626C6501000474686973010015
 * 4C6A766D2F636C617A7A2F54657374436C6173733B010003696E630100032829490900010
 * 0130C0005000601000A536F7572636546696C6501000E54657374436C6173732E6A617661
 * 0021000100030000000100020005000600000002000100070008000100090000002F00010
 * 001000000052AB7000AB100000002000C00000006000100000003000D0000000C00010000
 * 0005000E000F0000000100100011000100090000003100020001000000072AB400120460A
 * C00000002000C00000006000100000006000D0000000C000100000007000E000F00000001
 * 0014000000020015
 * 
 * ����Ϊʹ��javap -verbose����Ը�class�ļ��ķ���:
Classfile /C:/Users/Admin/Desktop/TestClass.class
  Last modified 2017-5-3; size 373 bytes
  MD5 checksum 5c402900d76ce3794d32e997e1748e9f
  Compiled from "TestClass.java"
public class jvm.clazz.TestClass
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Class              #2             // jvm/clazz/TestClass
   #2 = Utf8               jvm/clazz/TestClass
   #3 = Class              #4             // java/lang/Object
   #4 = Utf8               java/lang/Object
   #5 = Utf8               m
   #6 = Utf8               I
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Methodref          #3.#11         // java/lang/Object."<init>":()V
  #11 = NameAndType        #7:#8          // "<init>":()V
  #12 = Utf8               LineNumberTable
  #13 = Utf8               LocalVariableTable
  #14 = Utf8               this
  #15 = Utf8               Ljvm/clazz/TestClass;
  #16 = Utf8               inc
  #17 = Utf8               ()I
  #18 = Fieldref           #1.#19         // jvm/clazz/TestClass.m:I
  #19 = NameAndType        #5:#6          // m:I
  #20 = Utf8               SourceFile
  #21 = Utf8               TestClass.java
{
  public jvm.clazz.TestClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #10                 // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 27: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Ljvm/clazz/TestClass;

  public int inc();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: getfield      #18                 // Field m:I
         4: iconst_1
         5: iadd
         6: ireturn
      LineNumberTable:
        line 30: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  this   Ljvm/clazz/TestClass;
}
SourceFile: "TestClass.java"
 */
