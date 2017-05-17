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
 * ʹ��java8����ʱ��������
 *
 */
public class TestZone {
	@Test
	public void testZone(){
		//ʹ��ZoneId��ȡָ��ʱ����ʱ���
		//��ȡ����ʱ���ֶ�
		print(ZoneId.getAvailableZoneIds());
		//����ǰ��TimeZone����ת��Ϊ���ڵ�ZoneId��
		ZoneId zd = TimeZone.getDefault().toZoneId();
		LocalDate d = LocalDate.of(2017, 3, 12);
		ZonedDateTime zdt = d.atStartOfDay(zd);
		print(zdt);
		
		//ʹ�ñ������ϵͳ��
		LocalDate d1 = LocalDate.now();
		MinguoDate md = MinguoDate.from(d1);
		print(md);
	}

}













