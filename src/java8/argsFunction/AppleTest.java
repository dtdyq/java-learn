package java8.argsFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 行为参数化：
 *
 */
public class AppleTest {
	//删选绿苹果：第一个解决方案：
	public static List<Apple> filterGreen(List<Apple> apples){
		List<Apple> res = new ArrayList<>();
		for (Apple apple:apples){
			if ("green".equals(apple.getColor())){
				res.add(apple);
			}
		}
		return res;
	}
	//改进版：把颜色作为参数：
	public static List<Apple> filterColor(List<Apple> apples,String color){
		List<Apple> res = new ArrayList<>();
		for (Apple apple:apples){
			if (color.equals(apple.getColor())){
				res.add(apple);
			}
		}
		return res;
	}
	//当用户要求通过重量来筛选苹果时，又需要写相同的逻辑：
	public static List<Apple> filterWeight(List<Apple> apples,double weight){
		List<Apple> res = new ArrayList<>();
		for (Apple apple:apples){
			if (apple.getWeight() >= weight){
				res.add(apple);
			}
		}
		return res;
	}
	//当客户要求即使用颜色又使用重量来筛选苹果时，，，尴尬！
	
    //*********************************************************************
	//更优秀的方案：行为参数化：给予不同的行为，作为相同的参数，产生不同的结果
	//根据抽象条件进行筛选：
	public static List<Apple> filterApple(List<Apple> apples,ApplePredicate p){
		List<Apple> res = new ArrayList<>();
		apples.forEach(apple->{
			if (p.test(apple)){
				res.add(apple);
			}
		});
		return res;
	}
	
	//eg:定义行为参数，实现特定的格式化输出：
	public static void printApples(List<Apple> apples,AppleFormater formater){
		apples.forEach(apple->{
			String res = formater.accept(apple);
			System.out.println(res);
		});
	}
	@Test
	public void TestPrintApples(){
		List<Apple> list = new ArrayList<>();
		list.add(new Apple("green",23.43));
		list.add(new Apple("red",54.34));
		list.add(new Apple("blue",24.99));
		list.add(new Apple("green",87.21));
		printApples(list,new AppleSimpleFormater());
	}
	@Test
	public void TestSelectApple(){
		//虽然通过接口的方式简化了逻辑，但每次都需要new出具体的子类依然是很繁琐的一件事
		//可以通过使用匿名内部类进一步简化代码：
		List<Apple> list = Arrays.asList(new Apple("green",54.2),
				new Apple("red",76.2),
				new Apple("white",65)
				);
		
		List<Apple> res = filterApple(list,new ApplePredicate(){
			@Override
			public boolean test(Apple apple) {
				return apple.getWeight()>60.0;
			}
			
		});
		
		printApples(res,new AppleFormater(){
			@Override
			public String accept(Apple apple) {
				return apple.getColor()+" : "+apple.getWeight();
			}
			
		});
		
		
		//然而匿名内部类并不是一个最好的方法，其自身也有很多缺陷，应该使用lambda
		
		List<Apple> resl = filterApple(list,e->e.getColor().equals("green"));
		
		printApples(resl,e->String.valueOf(e.getWeight())+" : "+e.getColor());
		
	    //更通用的泛型lambda表达式，见AppleLambdaTest
		
	}
}












