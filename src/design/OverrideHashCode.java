package design;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 重写equals方法的同时要重写hashCode方法
 * 要保证使用equals方法判断为true的两个对象应该返回相同的hashcode，反之则不然
 * 
 * 好的hashcode:为不相等的对象产生不同的hashcode
 *
 */
public class OverrideHashCode {
	@Test
	public void test1(){
		//没有添加hashCode时下面的类出现的错误：
		Map<PhoneNumber,String> map = new HashMap<>();
		map.put(new PhoneNumber(123,656,9988),"janny");
		//很遗憾，返回的是null
		System.out.println(map.get(new PhoneNumber(123,656,9988)));
	}
}
class PhoneNumber{
	private final short areaCode;
   	private final short prefix;
	private final short lineNumber;
	public PhoneNumber(int i, int j, int k) {
		this.areaCode = (short) i;
		this.prefix = (short) j;
		this.lineNumber = (short) k;
	}
	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o instanceof PhoneNumber){
			PhoneNumber p = (PhoneNumber) o;
			return Short.compare(this.areaCode, p.areaCode)==0&&
					Short.compare(this.prefix, p.prefix)==0&&
					Short.compare(this.lineNumber,p.lineNumber)==0;
		}
		return false;
	}
	//重写hashcode方法：
	@Override
	public int hashCode(){
		int res = 17;
		res = 31*res + areaCode;
		res = 31*res + prefix;
		res = 31*res + lineNumber;
		return res;
	}
}


















