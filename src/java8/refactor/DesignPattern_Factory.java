package java8.refactor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * @author dtdyq
 * 
 * 代码重构之使用lambda实现面向对象的设计模式
 * 
 * 工厂模式：无需向客户端暴露实例化的逻辑而完成对象的创建
 * 
 * eg：银行根据用户的要求生产相应的产品
 *
 */
public class DesignPattern_Factory {
	@Test
	public void test(){
		//通过普通代码实现的工厂方法：
		Product p = ProductFactory.getProduct("load");
		p.pro();
		
		//通过lambda实现：
		Supplier<Product> sp = Load::new;
		Load l = (Load) sp.get();
		l.pro();
		
		Map<String,Supplier<Product>> map = new HashMap<>();
		map.put("load", Load::new);
		map.put("stock", Stock::new);
		map.put("bond", Bond::new);
		
		Bond b = (Bond) map.get("bond").get();
		b.pro();
	}
}
interface Product{
	void pro();
}
//产品
class Load implements Product{
	public void pro(){
		System.out.println("get load");
	}
}
class Stock implements Product{
	public void pro(){
		System.out.println("get Stock");
	}
}
class Bond implements Product{
	public void pro(){
		System.out.println("get Bond");
	}
}
//生产工厂
class ProductFactory{
	public static Product getProduct(String pName){
		switch(pName){
		case "load":return new Load();
		case "stock":return new Load();
		case "bond":return new Load();
		default:return null;
		}
	}
}














