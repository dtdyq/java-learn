package java8.defaultandoptional;

import java.util.Optional;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 使用Optional对NullError的例子进行改进
 *
 */
public class TestOptional {
	//使用Optional的获取名字方法：
	public static String getCarInsuranceName(Optional<Persono> persono){
		//flatmap:用于将嵌套的Optional扁平化为一个Optional
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