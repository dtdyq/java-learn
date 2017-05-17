package java8.stream;

import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author dtdyq
 * 
 * ������
 *
 */
public class DishManager {
	public static List<Dish> list;
	static{
		list = Arrays.asList(
				new Dish("Pork",false,800,Dish.Type.MEAT),
				new Dish("beef",false,700,Dish.Type.MEAT),
				new Dish("chicken",false,400,Dish.Type.MEAT),
				new Dish("french fries",true,530,Dish.Type.OTHER),
				new Dish("rice",true,350,Dish.Type.OTHER),
				new Dish("season fruit",true,120,Dish.Type.OTHER),
				new Dish("Pizza",true,550,Dish.Type.OTHER),
				new Dish("prawns",false,300,Dish.Type.FISH),
				new Dish("salmon",false,450,Dish.Type.FISH)
				);
	}
}
