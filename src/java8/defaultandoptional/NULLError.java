package java8.defaultandoptional;
/**
 * 
 * @author dtdyq
 * 
 * 示例显示null对程序可读性、可维护性和正确性的影响
 *
 */
public class NULLError {
	//该方法极易出现NullPointerException
	public static String getCarInsuranceName(Person person){
		return person.getCar().getInsurance().getName();
	}
	//改进：却增加了嵌套深度，降低了可维护性
	public static String getCarInsuranceNamep(Person person){
		if (person != null){
			Car car = person.getCar();
			if (car != null){
				Insurance insurance = car.getInsurance();
				return insurance.getName();
			}
		}
		return "Unknow";
	}
	//改进：增加了返回路径，使得程序难以维护
	public static String getCarInsuranceNamepp(Person person){
		if (person == null){
			return "Unknow";
		}
		Car car = person.getCar();
		if (car == null){
			return "Unknow";
		}
		Insurance insurance = car.getInsurance();
		if(insurance == null){
			return "Unknow";
		}
		return insurance.getName();
	}
	
}
class Insurance{
	private String name;
	public String getName(){
		return name;
	}
}
class Car{
	private Insurance insurance;
	public Insurance getInsurance(){
		return insurance;
	}
}
class Person{
	private Car car;
	public Car getCar(){
		return car;
	}
}


















