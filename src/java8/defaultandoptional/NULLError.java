package java8.defaultandoptional;
/**
 * 
 * @author dtdyq
 * 
 * ʾ����ʾnull�Գ���ɶ��ԡ���ά���Ժ���ȷ�Ե�Ӱ��
 *
 */
public class NULLError {
	//�÷������׳���NullPointerException
	public static String getCarInsuranceName(Person person){
		return person.getCar().getInsurance().getName();
	}
	//�Ľ���ȴ������Ƕ����ȣ������˿�ά����
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
	//�Ľ��������˷���·����ʹ�ó�������ά��
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


















