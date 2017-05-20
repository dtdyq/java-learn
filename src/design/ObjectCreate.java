package design;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 尽量重用对象而不频繁创建新的对象
 *
 */
public class ObjectCreate {
	@Test
	public void test(){
		Person p1 = new Person(new Date(System.currentTimeMillis()));
		long t1 = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			p1.isPeriod();
		}
		System.out.println((System.currentTimeMillis()-t1)+" ms");
		PersonSuper p2 = new PersonSuper(new Date(System.currentTimeMillis()));
		t1 = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			p2.isPeriod();
		}
		System.out.println((System.currentTimeMillis()-t1)+" ms");
	}
}
//以下两个类用来检测某人是否在固定的时间段内出生：
class Person{
	private Date birthDate;	
	public Person(Date date){
		this.birthDate = date;
	}
	public boolean isPeriod(){
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.set(1990, Calendar.JANUARY, 0);
		Date bs = gmtCal.getTime();
		gmtCal.set(2010, Calendar.JANUARY, 0);
		Date be = gmtCal.getTime();
		return birthDate.compareTo(bs)>=0&&
				birthDate.compareTo(be)<=0;
	}
}
//更高效的方式：
class PersonSuper{
	private Date birthDate;	
	private static Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	private static Date bs;
	private static Date be;
	static{
		gmtCal.set(1990, Calendar.JANUARY, 0);
		bs = gmtCal.getTime();
		gmtCal.set(2010, Calendar.JANUARY, 0);
		be = gmtCal.getTime();
	}
	public PersonSuper(Date date){
		this.birthDate = date;
	}
	public boolean isPeriod(){
		return birthDate.compareTo(bs)>=0&&
				birthDate.compareTo(be)<=0;
	}
}





















