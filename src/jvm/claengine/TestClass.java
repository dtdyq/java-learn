package jvm.claengine;
/**
 * 
 * @author dtdyq
 * 
 * jvm执行的class文件结构：
 * 		无符号数：基本数据类型，以u1、u2、u4、u8分别表示1、2、4、8个字节，用于描述
 * 				数字、索引引用、数量值、字符串值
 * 		表：多个无符号数或其他表项组成的复合数据结构
 ************************************************************************
 *magic number：魔数：class文件的头四个字节，用来确认这个文件是否是能被jvm接受的
 *class文件：CAFEBABE
 *
 *接下来的四个字节是class文件的版本号：其中5、6字节是次版本号，7、8字节是主版本号，
 *java的版本号从45开始，jdk1.1之后，每个jdk大版本发布后在主版本号上加一，00000034
 *表示次版本号为0，主版本号为0034，即1.8
 *
 *紧接着主次版本号之后的是常量池入口，常量池可以理解为Class文件之中的资源仓库，常量池
 *入口处放置一项u2类型的数据，代表常量池容量计数值，从1开始，如下面的十六进制字节码的
 *0016即十进制的22，表示常量池中有21项常量。常量池中主要用于存放字面量(Literal)和
 *符号引用(Symbolic Reference)，字面量即文本字符串、声明为final的常量；而符号引用包括
 *类和接口的权限定名，字段的名称和描述符、方法的名称和描述符。
 *	常量池中的每一项常量都是一个表，共表示十几种不同的表结构数据，每个表的第一位是一个u1
 *类型的标志位，代表这个常量代表哪种常量类型
 *
 *紧跟着常量池之后的两个字节代表访问标志，用于识别一些类或者接口层次的访问信息，包括：该
 *class文件是来还是接口，是否定义为public类型，是否定义为abstract类型，是否被声明为
 *final
 *
 *访问标志之后是类索引、父类索引、接口索引集合，分别为u2、u2和一组u2类型的集合，这些数据
 *指向CONSTANT_Utf8_info类型的常量，该常量中描述了类或接口的全限定名：
 *		   #1 = Class              #2             // jvm/clazz/TestClass
		   #2 = Utf8               jvm/clazz/TestClass
		   #3 = Class              #4             // java/lang/Object
		   #4 = Utf8               java/lang/Object
 *对于接口，第一个u2类型字段表示，实现的接口数量，后面的每个u2指向对应接口的全限定名
 *
 *field_info:字段表用于描述接口或者类中声明的变量，字段包括类级变量和实例级变量，不
 *包括方法内部声明的局部变量
 *每个字段由access_flag、name_index、descripter_index组成，分别用于描述字段修饰符、
 *字段的简单名称和字段和方法的描述符。方法描述符用于描述字段的数据类型、方法的参数列表
 *和返回值
 *
 *方法表集合基本格式和字段表相同
 *
 *属性表用于描述某些场景专有的信息
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
 * 上面的类的class文件十六进制信息：
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
 * 以下为使用javap -verbose命令对该class文件的分析:
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
