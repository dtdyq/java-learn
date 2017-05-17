package java8.functional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.Arrays.*;

/**
 * Created by dtdyq on 2017/4/1.
 *
 */
public class TestCollection {
    public static void main(String[] args){
        //使用collect(toList())生成列表：
        List<String> list= Stream.of("a","c","b","z","j").collect(Collectors.toList());
        System.out.println(list);
        
        //use map to transform a stream to new stream:
        List<String> lu=Stream.of("a","y","u","k")
                .map(str->str.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(lu);
        
        //filter:traverse data and check the element:
        List<String> lf=Stream.of("hfiew","873gefw","nf34","9fhwqd")
                .filter(s->isDigit(s.charAt(0))).collect(Collectors.toList());
        System.out.println(lf);
        
        //flatMap:combinate multiStream to one stream:
        List<Integer> li=Stream.of(asList(1,2),asList(5,8))
                .flatMap(num->num.stream())
                .collect(Collectors.toList());
        System.out.println(li);
        List<String> ls=asList("ifeywg","viewur","qiefg","qicne");
        Optional<String> shorts=ls.stream()
                .min(Comparator.comparing(l->l.length()));
        System.out.println(shorts.get());
        
        //使用reduce实现累加：
        int count=Stream.of(1,2,3,4,5,6,7,8,9,10)
                .reduce(0,(acc,ele)->acc+ele);
        System.out.println(count);

    }
    static boolean isDigit(char c){
        return (int)c<=9&&(int)c>=0;
    }
}
