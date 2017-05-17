package java8.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.MinguoDate;
import java.util.TimeZone;

import org.junit.Test;

import static java8.time.DateTime.*;

/**
 * 
 * @author dtdyq
 * 
 * 使用java8处理时区和历法
 *
 */
public class TestZone {
	@Test
	public void testZone(){
		//使用ZoneId获取指定时区的时间点
		//获取所有时区字段
		print(ZoneId.getAvailableZoneIds());
		//将以前的TimeZone对象转换为现在的ZoneId：
		ZoneId zd = TimeZone.getDefault().toZoneId();
		LocalDate d = LocalDate.of(2017, 3, 12);
		ZonedDateTime zdt = d.atStartOfDay(zd);
		print(zdt);
		
		//使用别的日历系统：
		LocalDate d1 = LocalDate.now();
		MinguoDate md = MinguoDate.from(d1);
		print(md);
	}

}













