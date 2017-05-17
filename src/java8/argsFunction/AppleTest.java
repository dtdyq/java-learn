package java8.argsFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ��Ϊ��������
 *
 */
public class AppleTest {
	//ɾѡ��ƻ������һ�����������
	public static List<Apple> filterGreen(List<Apple> apples){
		List<Apple> res = new ArrayList<>();
		for (Apple apple:apples){
			if ("green".equals(apple.getColor())){
				res.add(apple);
			}
		}
		return res;
	}
	//�Ľ��棺����ɫ��Ϊ������
	public static List<Apple> filterColor(List<Apple> apples,String color){
		List<Apple> res = new ArrayList<>();
		for (Apple apple:apples){
			if (color.equals(apple.getColor())){
				res.add(apple);
			}
		}
		return res;
	}
	//���û�Ҫ��ͨ��������ɸѡƻ��ʱ������Ҫд��ͬ���߼���
	public static List<Apple> filterWeight(List<Apple> apples,double weight){
		List<Apple> res = new ArrayList<>();
		for (Apple apple:apples){
			if (apple.getWeight() >= weight){
				res.add(apple);
			}
		}
		return res;
	}
	//���ͻ�Ҫ��ʹ����ɫ��ʹ��������ɸѡƻ��ʱ���������Σ�
	
    //*********************************************************************
	//������ķ�������Ϊ�����������費ͬ����Ϊ����Ϊ��ͬ�Ĳ�����������ͬ�Ľ��
	//���ݳ�����������ɸѡ��
	public static List<Apple> filterApple(List<Apple> apples,ApplePredicate p){
		List<Apple> res = new ArrayList<>();
		apples.forEach(apple->{
			if (p.test(apple)){
				res.add(apple);
			}
		});
		return res;
	}
	
	//eg:������Ϊ������ʵ���ض��ĸ�ʽ�������
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
		//��Ȼͨ���ӿڵķ�ʽ�����߼�����ÿ�ζ���Ҫnew�������������Ȼ�Ǻܷ�����һ����
		//����ͨ��ʹ�������ڲ����һ���򻯴��룺
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
		
		
		//Ȼ�������ڲ��ಢ����һ����õķ�����������Ҳ�кܶ�ȱ�ݣ�Ӧ��ʹ��lambda
		
		List<Apple> resl = filterApple(list,e->e.getColor().equals("green"));
		
		printApples(resl,e->String.valueOf(e.getWeight())+" : "+e.getColor());
		
	    //��ͨ�õķ���lambda���ʽ����AppleLambdaTest
		
	}
}












