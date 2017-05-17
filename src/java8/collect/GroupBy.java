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
 * ����
 *
 */
public class GroupBy {
	public static enum CaloriesLevel{DIET,NORMAL,FAT}
	List<Dish> menu = DishManager.list;
	@Test
	public void testGroupBy(){
		//�����Ȱ���������з��飺
		Map<Dish.Type,List<Dish>> dishByType = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType));
		System.out.println(dishByType);
		//�Զ�����ࣺ���������ղ�ͬ��ֵ��Ϊdiet��normal��fat���֣�
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
		//ʹ�ö༶���飺�Ȱ��ղ˵�������з��飬�ٽ�ͬ����Ĳ˰����������з���
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
		//����������������ռ���
		Map<Dish.Type,Long> typeCount = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,Collectors.counting()));
		System.out.println(typeCount);
		//�ҵ�ÿ���������������Ĳ��ȣ�
		Map<Dish.Type,Optional<Dish>> typeCal = menu.stream()
				.collect(Collectors.groupingBy(
						Dish::getType,Collectors.maxBy(
								Comparator.comparingDouble(Dish::getCalories))));
		System.out.println(typeCal);
		//������������ת����colllectingAndThen:Ҫת�����ռ�����ת������
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
		//����groupBy���ӣ�
		//����ÿ�ֲ��ȵ���������
		Map<Dish.Type,Double> typeSum = menu.stream()
				.collect(Collectors.groupingBy(
						Dish::getType,Collectors.summingDouble(Dish::getCalories)));
		System.out.println(typeSum);
		
		//����ÿ�ֲ��ȶ�����ЩCaloriesLevel��
		//mapping����һ�����������е�Ԫ�ؽ��б任���ڶ��������Ա任�Ľ������Collect
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
	    //�����set��û��ȷ�����巵��ʲô�������ͣ�����ͨ��һ�·���ָ����
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















