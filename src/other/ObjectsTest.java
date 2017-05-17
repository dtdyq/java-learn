package other;
import java.util.*;
/**
 * Created by Admin on 2017/4/4.
 * java7新增的Objects工具类，是“空指针”安全的
 */
public class ObjectsTest {
    static ObjectsTest test;

    public static void main(String[] args){
        System.out.println(Objects.hashCode(test));
        System.out.println(Objects.toString(test));
        System.out.println(Objects.compare(23,34,new Cmp()));

    }

    public static class Cmp implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return 0;
        }
    }
}
