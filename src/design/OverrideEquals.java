package design;
/**
 * 
 * @author dtdyq
 * 
 * 正确地重写equals方法：
 * 	1、使用==判断是否是同一个对象实例
 *  2、使用instanceof来检测是否是同一类
 *  3、进行类型转换
 *  4、对对象的关键属性进行比较
 *  5、测试是否符合自反性、传递性、一致性、持久性
 *  
 *  注意：重写equals时一定要重写hashCode方法
 *
 */
public class OverrideEquals {
	private String fstr;
	private Integer fint;
	public OverrideEquals(String fstr, Integer fint) {
		super();
		this.fstr = fstr;
		this.fint = fint;
	}
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		}
		if(o instanceof OverrideEquals){
			OverrideEquals oe = (OverrideEquals) o;
			return this.fstr.equals(oe.fstr) && Integer.compare(this.fint, oe.fint)==0;
		}
		return false;
	}
}






