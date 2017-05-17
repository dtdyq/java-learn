package java8.refactor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import java8.collect.GroupBy.CaloriesLevel;
import java8.stream.Dish;
import java8.stream.DishManager;

/**
 * 
 * @author dtdyq
 * 
 * 重构之改善代码的可读性
 *
 */
public class Readable {
	List<Dish> menu = DishManager.list;
	
	//匿名类-->lambda：
	@SuppressWarnings("unused")
	@Test
	public void first(){
		//匿名类：
		Runnable r1 = new Runnable(){
			@Override
			public void run() {
				System.out.println("匿名类");
			}
		};
		//lambda:
		Runnable r2 = ()->{
			System.out.println("lambda");
		};
		//有时候lambda在作为参数传递时可能产生二义性
	}
	//从lambda到方法引用
	@Test
	public void second(){
		//有时候，lambda可能并没有方法引用直观：
		//如之前对菜肴进行分类的程序：
		//自定义分类：将热量按照不同的值分为diet、normal、fat三种：
		Map<CaloriesLevel, Dish> dishByLevel = menu.stream()
				.collect(Collectors.groupingBy(e->{
					double cal = e.getCalories();
					if(cal<400){
						return CaloriesLevel.DIET;
					}else if(cal>=400 && cal<=700){
						return CaloriesLevel.NORMAL;
					}else {
						return CaloriesLevel.FAT;
					}
				},Collectors.collectingAndThen(
						Collectors.maxBy(
								Comparator.comparing(Dish::getCalories)), Optional::get)));
		//但该程序中间的判断代码并不直观易读，在Dish中添加相应的函数，则可以使用方法
		//引用代替lambda：
		Map<CaloriesLevel,Dish> dishByLevelRef = menu.stream()
				.collect(Collectors.groupingBy(Dish::getCalLevel,
						Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
						Optional::get)
						));
		System.out.println(dishByLevel);		
		System.out.println(dishByLevelRef);
		//使用特定功能的辅助方法比普通的操作更易读：
		double red = menu.stream()
				.map(Dish::getCalories)
				.reduce(0, (a,b)->a+b);
		double sumi = menu.stream()
				.collect(Collectors.summingDouble(Dish::getCalories));
		System.out.println(red+"  "+sumi);
	}
}















