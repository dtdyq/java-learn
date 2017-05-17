package java8.refactor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * @author dtdyq
 * 
 * �����ع�֮ʹ��lambdaʵ�������������ģʽ
 * 
 * ����ģʽ��������ͻ��˱�¶ʵ�������߼�����ɶ���Ĵ���
 * 
 * eg�����и����û���Ҫ��������Ӧ�Ĳ�Ʒ
 *
 */
public class DesignPattern_Factory {
	@Test
	public void test(){
		//ͨ����ͨ����ʵ�ֵĹ���������
		Product p = ProductFactory.getProduct("load");
		p.pro();
		
		//ͨ��lambdaʵ�֣�
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
//��Ʒ
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
//��������
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














