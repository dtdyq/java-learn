package java8.stream;

import java8.collect.GroupBy.CaloriesLevel;
/**
 * 
 * @author dtdyq
 * 
 * ∏®÷˙¿‡
 *
 */
public class Dish {

	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		super();
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}
	public CaloriesLevel getCalLevel(){
		double cal = getCalories();
		if(cal<400){
			return CaloriesLevel.DIET;
		}else if(cal>=400 && cal<=700){
			return CaloriesLevel.NORMAL;
		}else {
			return CaloriesLevel.FAT;
		}
	}
	@Override
	public String toString() {
		return "Dish [name=" + name + ", vegetarian=" + vegetarian + ", calories=" + calories + ", type=" + type + "]";
	}
	public static enum Type{MEAT,FISH,OTHER}
}
