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
 * java�µ����ں�ʱ����
 *
 */
public class DateTime {
	@Test
	public void testDate(){
		//LocalDate:
		//ʹ�þ�̬������������ʵ����
		LocalDate date = LocalDate.of(2018, 1, 12);
		print(date.getYear());
		print(date.getMonth());
		print(date.getDayOfWeek());
		print(date.getDayOfMonth());
		print(date.isLeapYear());
		
		//��ȡ��ǰ���ڣ�
		LocalDate date2 = LocalDate.now();
		print(date2);
		//ʹ��ChronoField��ȡLocalDate��ֵ��
		print(date2.get(ChronoField.YEAR));
		print(date2.get(ChronoField.MONTH_OF_YEAR));
	}
	@Test
	public void testTime(){
		//LocalTieme�÷�������Localdate��ͬ��
		LocalTime time = LocalTime.of(12, 34, 54);
		print(time.getHour(),time.getMinute(),time.getSecond());
		print(LocalTime.now());
		
		//�����ַ�����
		LocalDate d = LocalDate.parse("2017-11-23");
		LocalTime t = LocalTime.parse("11:23:44");
		print(d,t);
		
		//LocalDate��LocalTime������������
	    LocalTime t1 = t.withHour(21);
	    print(t1);
	    LocalTime t2 = t.with(ChronoField.MINUTE_OF_HOUR,10);
	    print(t2);
		//����Եķ�ʽ�޸�ʱ�������ֵ��
	    LocalTime t3 = t.plusHours(4);
	    print(t3);
	    LocalTime t4 = t.minusMinutes(13);
	    print(t4);
	}
	@Test
	public void testDateTime(){
		LocalDateTime dt = LocalDateTime.of(2017, Month.APRIL,14,11,23,45);
		print(dt);
		
		//��LocalDate��LocalTime��LocalDateTime֮��ת��
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
		//Instant�����ڻ���ʹ�õ�ʱ���ʽ��ͨ��ʹ�����������Ϊʱ�䵥λ
		//Duration����ʾһ��ʱ�䷶Χ��
		Duration d = Duration.between(LocalTime.of(11, 43), LocalTime.of(12, 21));
		print(d);
		Duration d1 = Duration.between(
				Instant.ofEpochMilli(1), Instant.ofEpochMilli(1+1_000_000_000));
		print(d1);
		//Period:��ʾ�����ȳ�ʱ������
		Period p = Period.between(LocalDate.now(), LocalDate.of(2018, 1, 1));
		print(p);
	}
	@Test
	public void testFormat(){
		//ʹ��DateTimeFormatter���ж�ʱ������ڵĶ��ƣ�
		//��ָ����ʽ�����
		LocalDate d = LocalDate.now();
		String s1 = d.format(DateTimeFormatter.BASIC_ISO_DATE);
		String s2 = d.format(DateTimeFormatter.ISO_LOCAL_DATE);
		print(s1,s2);
		//��ָ����ʽ�����ַ�����
		LocalDate d2 = LocalDate.parse("20181212", DateTimeFormatter.BASIC_ISO_DATE);
		print(d2);
		
		//ʹ��ָ����ģʽ��ʽ���ַ�����
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/dd/MM");
		String s3 = d.format(df);
		print(s3);
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("MM  dd  yyyy",Locale.CHINA);
		print(d.format(df2));
		
		//�����Զ���Ľ�������
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














