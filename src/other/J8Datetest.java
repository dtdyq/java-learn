package other;

import java.time.*;
import java.time.format.*;

/**
 * Created by Admin on 2017/4/5.
 * java8 新增的date类使用
 */
public class J8Datetest {
    public static void main(String[] args){
        //Clock使用方法：
        Clock clock=Clock.systemUTC();
        System.out.println(clock.instant());
        //获取clock对应的毫秒数：
        System.out.println(clock.millis());
        //Duration使用方法：
        Duration d=Duration.ofSeconds(6000);
        System.out.println(d.toMinutes()+" minutes");
        System.out.println(d.toHours()+" hours");
        //在clock的基础上增加6000秒，返回新的Clock
        Clock c2=Clock.offset(clock,d);
        System.out.println(c2.instant());

        //Instant使用方法：
        Instant in=Instant.now();
        System.out.println(in);
        //向in新添加6000秒，返回新的Instant：
        Instant in2=in.plusSeconds(6000);
        System.out.println(in2);

        //LocalDate
        LocalDate date=LocalDate.now();
        System.out.println(date);
        //获取2019年的第160天：
        LocalDate l2=LocalDate.ofYearDay(2019,160);
        System.out.println(l2);
        //设置时间：
        System.out.println(LocalDate.of(2018,Month.MAY,4));
        //LocalTime 用法：
        LocalTime time= LocalTime.now();
        System.out.println(time);

        //LocalDateTime：
        LocalDateTime dt=LocalDateTime.now();
        System.out.println(dt);

        //DateTimeFormatter对象：
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









