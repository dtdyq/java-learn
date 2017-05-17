package java8.collect;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import java.util.*;
import java8.stream.Dish;
import java8.stream.DishManager;



/**
 * 
 * @author dtdyq
 * 
 * 分组
 *
 */
public class GroupBy {
	public static enum CaloriesLevel{DIET,NORMAL,FAT}
	List<Dish> menu = DishManager.list;
	@Test
	public void testGroupBy(){
		//将菜肴按照种类进行分组：
		Map<Dish.Type,List<Dish>> dishByType = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType));
		System.out.println(dishByType);
		//自定义分类：将热量按照不同的值分为diet、normal、fat三种：
		Map<CaloriesLevel,List<Dish>> dishByLevel = menu.stream()
				.collect(Collectors.groupingBy(e->{
					double cal = e.getCalories();
					if(cal<400){
						return CaloriesLevel.DIET;
					}else if(cal>=400 && cal<=700){
						return CaloriesLevel.NORMAL;
					}else {
						return CaloriesLevel.FAT;
					}
				}));
		System.out.println(dishByLevel);
	}
	@Test
	public void testMultiGroup(){
		//使用多级分组：先按照菜的种类进行分组，再将同种类的菜按照热量进行分组
		Map<Dish.Type,Map<CaloriesLevel,List<Dish>>> multi = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,
						Collectors.groupingBy(e->{
							double cal = e.getCalories();
							if(cal<400){
								return CaloriesLevel.DIET;
							}else if(cal>=400 && cal<=700){
								return CaloriesLevel.NORMAL;
							}else {
								return CaloriesLevel.FAT;
							}
						})
						));
		System.out.println(multi);
	}
	@Test
	public void testSubGroup(){
		//划分子组进行数据收集：
		Map<Dish.Type,Long> typeCount = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,Collectors.counting()));
		System.out.println(typeCount);
		//找到每种类型中热量最大的菜肴：
		Map<Dish.Type,Optional<Dish>> typeCal = menu.stream()
				.collect(Collectors.groupingBy(
						Dish::getType,Collectors.maxBy(
								Comparator.comparingDouble(Dish::getCalories))));
		System.out.println(typeCal);
		//将分组结果进行转换：colllectingAndThen:要转换的收集器，转换函数
		Map<Dish.Type,Dish> typeConvert = menu.stream()
				.collect(Collectors.groupingBy(
						Dish::getType,
						Collectors.collectingAndThen(
								Collectors.maxBy(
								Comparator.comparingDouble(Dish::getCalories)),Optional::get)));
		System.out.println(typeConvert);
	}
	@Test
	public void testExample(){
		//其他groupBy例子：
		//计算每种菜肴的总热量：
		Map<Dish.Type,Double> typeSum = menu.stream()
				.collect(Collectors.groupingBy(
						Dish::getType,Collectors.summingDouble(Dish::getCalories)));
		System.out.println(typeSum);
		
		//计算每种菜肴都有哪些CaloriesLevel：
		//mapping：第一个参数对流中的元素进行变换，第二个参数对变换的结果进行Collect
	    Map<Dish.Type,Set<CaloriesLevel>> typeMap = menu.stream()
	    		.collect(Collectors.groupingBy(Dish::getType,
	    				Collectors.mapping(e->{
	    					double cal = e.getCalories();
							if(cal<400){
								return CaloriesLevel.DIET;
							}else if(cal>=400 && cal<=700){
								return CaloriesLevel.NORMAL;
							}else {
								return CaloriesLevel.FAT;
							}
	    				}, Collectors.toSet())
	    				));
	    System.out.println(typeMap);
	    //上面的set并没有确定具体返回什么样的类型，可以通过一下方法指定：
	    Map<Dish.Type,Set<CaloriesLevel>> typeHashSet = menu.stream()
	    		.collect(Collectors.groupingBy(Dish::getType,
	    				Collectors.mapping(e->{
	    					double cal = e.getCalories();
							if(cal<400){
								return CaloriesLevel.DIET;
							}else if(cal>=400 && cal<=700){
								return CaloriesLevel.NORMAL;
							}else {
								return CaloriesLevel.FAT;
							}
	    				}, Collectors.toCollection(HashSet::new))
	    				));
	    System.out.println(typeHashSet);
	}
}















