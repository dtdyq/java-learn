package java8.defaultandoptional;

import java.util.Optional;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ʹ��Optional��NullError�����ӽ��иĽ�
 *
 */
public class TestOptional {
	//ʹ��Optional�Ļ�ȡ���ַ�����
	public static String getCarInsuranceName(Optional<Persono> persono){
		//flatmap:���ڽ�Ƕ�׵�Optional��ƽ��Ϊһ��Optional
		return persono.flatMap(Persono::getCar)
				.flatMap(Caro::getInsurance)
				.map(Insuranceo::getName)
				.orElse("Unknow");
	}
	@Test
	public void test(){
		Persono po = new Persono();
		String res = getCarInsuranceName(Optional.of(po));
		System.out.println(res);
	}
}
class Insuranceo{
	private String name;
	public String getName(){
		return name;
	}
}
class Caro{
	private Optional<Insuranceo> insurance;
	public Optional<Insuranceo> getInsurance(){
		return insurance;
	}
}
class Persono{
	private Optional<Caro> car;
	public Optional<Caro> getCar(){
		return car;
	}
}