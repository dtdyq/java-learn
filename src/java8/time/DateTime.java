package java8.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Locale;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * java新的日期和时间类
 *
 */
public class DateTime {
	@Test
	public void testDate(){
		//LocalDate:
		//使用静态工厂方法创建实例：
		LocalDate date = LocalDate.of(2018, 1, 12);
		print(date.getYear());
		print(date.getMonth());
		print(date.getDayOfWeek());
		print(date.getDayOfMonth());
		print(date.isLeapYear());
		
		//获取当前日期：
		LocalDate date2 = LocalDate.now();
		print(date2);
		//使用ChronoField读取LocalDate的值：
		print(date2.get(ChronoField.YEAR));
		print(date2.get(ChronoField.MONTH_OF_YEAR));
	}
	@Test
	public void testTime(){
		//LocalTieme用法基本和Localdate相同：
		LocalTime time = LocalTime.of(12, 34, 54);
		print(time.getHour(),time.getMinute(),time.getSecond());
		print(LocalTime.now());
		
		//解析字符串：
		LocalDate d = LocalDate.parse("2017-11-23");
		LocalTime t = LocalTime.parse("11:23:44");
		print(d,t);
		
		//LocalDate和LocalTime的其他方法：
	    LocalTime t1 = t.withHour(21);
	    print(t1);
	    LocalTime t2 = t.with(ChronoField.MINUTE_OF_HOUR,10);
	    print(t2);
		//以相对的方式修改时间和日期值：
	    LocalTime t3 = t.plusHours(4);
	    print(t3);
	    LocalTime t4 = t.minusMinutes(13);
	    print(t4);
	}
	@Test
	public void testDateTime(){
		LocalDateTime dt = LocalDateTime.of(2017, Month.APRIL,14,11,23,45);
		print(dt);
		
		//在LocalDate、LocalTime和LocalDateTime之间转换
		LocalDate d = LocalDate.of(2018, 11, 22);
		LocalTime t = LocalTime.of(21, 43, 24);
		LocalDateTime dt1 = LocalDateTime.of(d, t);
		print(dt1);
		LocalDateTime dt2 = d.atTime(t);
		LocalDateTime dt3 = t.atDate(d);
		print(dt2,dt3);
		
		LocalDate d1 = dt.toLocalDate();
		LocalTime t1 = dt.toLocalTime();
		print(d1,t1);
	}
	@Test
	public void testDurationPeriod(){
		//Instant：用于机器使用的时间格式，通常使用秒或纳秒作为时间单位
		//Duration：表示一个时间范围：
		Duration d = Duration.between(LocalTime.of(11, 43), LocalTime.of(12, 21));
		print(d);
		Duration d1 = Duration.between(
				Instant.ofEpochMilli(1), Instant.ofEpochMilli(1+1_000_000_000));
		print(d1);
		//Period:表示天数等长时间间隔：
		Period p = Period.between(LocalDate.now(), LocalDate.of(2018, 1, 1));
		print(p);
	}
	@Test
	public void testFormat(){
		//使用DateTimeFormatter进行对时间和日期的定制：
		//以指定格式输出：
		LocalDate d = LocalDate.now();
		String s1 = d.format(DateTimeFormatter.BASIC_ISO_DATE);
		String s2 = d.format(DateTimeFormatter.ISO_LOCAL_DATE);
		print(s1,s2);
		//以指定格式解析字符串：
		LocalDate d2 = LocalDate.parse("20181212", DateTimeFormatter.BASIC_ISO_DATE);
		print(d2);
		
		//使用指定的模式格式化字符串：
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/dd/MM");
		String s3 = d.format(df);
		print(s3);
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("MM  dd  yyyy",Locale.CHINA);
		print(d.format(df2));
		
		//构造自定义的解析器：
		DateTimeFormatter myFormat = new DateTimeFormatterBuilder()
				.appendText(ChronoField.DAY_OF_MONTH)
				.appendLiteral("--")
				.appendText(ChronoField.MONTH_OF_YEAR)
				.appendLiteral("  ")
				.appendText(ChronoField.YEAR)
				.toFormatter();
		print(LocalDate.now().format(myFormat));
	}
	public static void print(Object...o){
		Arrays.asList(o).forEach(System.out::println);
	}
}














