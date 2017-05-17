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
 * �ع�֮���ƴ���Ŀɶ���
 *
 */
public class Readable {
	List<Dish> menu = DishManager.list;
	
	//������-->lambda��
	@SuppressWarnings("unused")
	@Test
	public void first(){
		//�����ࣺ
		Runnable r1 = new Runnable(){
			@Override
			public void run() {
				System.out.println("������");
			}
		};
		//lambda:
		Runnable r2 = ()->{
			System.out.println("lambda");
		};
		//��ʱ��lambda����Ϊ��������ʱ���ܲ���������
	}
	//��lambda����������
	@Test
	public void second(){
		//��ʱ��lambda���ܲ�û�з�������ֱ�ۣ�
		//��֮ǰ�Բ��Ƚ��з���ĳ���
		//�Զ�����ࣺ���������ղ�ͬ��ֵ��Ϊdiet��normal��fat���֣�
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
		//���ó����м���жϴ��벢��ֱ���׶�����Dish�������Ӧ�ĺ����������ʹ�÷���
		//���ô���lambda��
		Map<CaloriesLevel,Dish> dishByLevelRef = menu.stream()
				.collect(Collectors.groupingBy(Dish::getCalLevel,
						Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
						Optional::get)
						));
		System.out.println(dishByLevel);		
		System.out.println(dishByLevelRef);
		//ʹ���ض����ܵĸ�����������ͨ�Ĳ������׶���
		double red = menu.stream()
				.map(Dish::getCalories)
				.reduce(0, (a,b)->a+b);
		double sumi = menu.stream()
				.collect(Collectors.summingDouble(Dish::getCalories));
		System.out.println(red+"  "+sumi);
	}
}















