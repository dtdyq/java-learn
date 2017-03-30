package design;

/**
 * 
 * @author dtdyq
 *1、面向接口编程
 *2、优先使用对象组合而非类继承
 *3、分层：典型层次划分：1、表现层（展示数据、人机交互、收集参数）；2、逻辑层（进行逻辑校验、判断、处理）；3、数据层（实现数据持久化、实现对象和持久化数据的双向映射）
 *4、层间交互的基本原则：
 *			表现层调用逻辑层、逻辑层调用数据层
 *			层间交互也应该通过接口交互
 *
 *其他基本设计原则：
 *		开闭原则：程序不修改代码的基础上进行新增代码
 *		依赖性倒置原则：依赖抽象而不依赖具体的实现
 *		接口隔离原则：不使用通用的接口，而对不同的用户使用不同的接口
 *		替换原则：子类应该可以替代父类并可以出现在任何父类出现的地方
 */
public class TestDesign {
	public static void main(String[] args){
		
	}
}
//优先使用对象组合而非类继承：
class A{
	public void f1(){};
	public void f2(){};
}
//继承不利于二次开发时的扩展
//class B extends A{
//	public void f3(){};
//}
class C{
	public void f4(){};
	public void f5(){};
}
//更好的方法：组合;
class B{
	//更好使用局部变量而非设计成类变量
//	private A a=new A();
	public void f1(){
		new A().f1();
	}
	public void f2(){
		new A().f2();
	}
	public void f3(){
		//......
	}
	public void f4(){
		new C().f4();
	}
	public void f5(){
		new C().f5();
	}
}
