package other;

import java.time.*;
import java.time.format.*;

/**
 * Created by Admin on 2017/4/5.
 * java8 ������date��ʹ��
 */
public class J8Datetest {
    public static void main(String[] args){
        //Clockʹ�÷�����
        Clock clock=Clock.systemUTC();
        System.out.println(clock.instant());
        //��ȡclock��Ӧ�ĺ�������
        System.out.println(clock.millis());
        //Durationʹ�÷�����
        Duration d=Duration.ofSeconds(6000);
        System.out.println(d.toMinutes()+" minutes");
        System.out.println(d.toHours()+" hours");
        //��clock�Ļ���������6000�룬�����µ�Clock
        Clock c2=Clock.offset(clock,d);
        System.out.println(c2.instant());

        //Instantʹ�÷�����
        Instant in=Instant.now();
        System.out.println(in);
        //��in�����6000�룬�����µ�Instant��
        Instant in2=in.plusSeconds(6000);
        System.out.println(in2);

        //LocalDate
        LocalDate date=LocalDate.now();
        System.out.println(date);
        //��ȡ2019��ĵ�160�죺
        LocalDate l2=LocalDate.ofYearDay(2019,160);
        System.out.println(l2);
        //����ʱ�䣺
        System.out.println(LocalDate.of(2018,Month.MAY,4));
        //LocalTime �÷���
        LocalTime time= LocalTime.now();
        System.out.println(time);

        //LocalDateTime��
        LocalDateTime dt=LocalDateTime.now();
        System.out.println(dt);

        //DateTimeFormatter����
        System.out.println("==================================");
        DateTimeFormatter[] dfm=new DateTimeFormatter[]{
              DateTimeFormatter.ISO_LOCAL_DATE,
              DateTimeFormatter.ISO_LOCAL_DATE_TIME,
              DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,FormatStyle.MEDIUM),
              DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG),
              DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss")
        };
        LocalDateTime ldt=LocalDateTime.now();
        for(int i=0;i<dfm.length;i++){
            System.out.println(ldt.format(dfm[i]));
            System.out.println(dfm[i].format(ldt));

        }
    }


}









