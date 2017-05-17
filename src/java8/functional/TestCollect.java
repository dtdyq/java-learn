package java8.functional;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dtdyq on 2017/4/2.
 *
 */
public class TestCollect {
    public static void main(String[] args){
        TreeSet<Integer> set= Stream.of(2,9,3,5,7,6)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);
        ArrayList<Artist> arts=new ArrayList<>();
        arts.add(new Artist("tom","london",23));
        arts.add(new Artist("kily","beijing",65));
        arts.add(new Artist("liqi","london",76));
        arts.add(new Artist("dtd","changzhi",12));
        String re=arts.stream()
                .map(Artist::getName)
                .collect(Collectors.joining("\",\"","[\"","\"]"));
        System.out.println(re);
    }
}
