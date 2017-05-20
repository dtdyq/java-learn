package design;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ��дequals������ͬʱҪ��дhashCode����
 * Ҫ��֤ʹ��equals�����ж�Ϊtrue����������Ӧ�÷�����ͬ��hashcode����֮��Ȼ
 * 
 * �õ�hashcode:Ϊ����ȵĶ��������ͬ��hashcode
 *
 */
public class OverrideHashCode {
	@Test
	public void test1(){
		//û�����hashCodeʱ���������ֵĴ���
		Map<PhoneNumber,String> map = new HashMap<>();
		map.put(new PhoneNumber(123,656,9988),"janny");
		//���ź������ص���null
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
	//��дhashcode������
	@Override
	public int hashCode(){
		int res = 17;
		res = 31*res + areaCode;
		res = 31*res + prefix;
		res = 31*res + lineNumber;
		return res;
	}
}


















