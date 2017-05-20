package design;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 使用静态工厂模式代替普通的构造器
 *
 */
public class StaticFactory {
	@Test
	public void test(){
		Services.register(new ProviderImpl());
		System.out.println(Services.newInstance());
	}
}
interface Service{
	void server();
}
interface Provider{
	Service newService();
}
class ProviderImpl implements Provider{

	@Override
	public Service newService() {
		return null;
	}
	
}
class Services{
	private Services(){};
	private static final Map<String,Provider> map = new HashMap<>();
	private static final String DEFAULT_NAME="def";
	public static void register(Provider pro){
		map.put(DEFAULT_NAME, pro);
	}
	public static void register(String name,Provider pro){
		map.put(name, pro);
	}
	public static Service newInstance(){
		return newInstance(DEFAULT_NAME);
	}
	public static Service newInstance(String name){
		Provider pro = map.get(name);
		if(pro==null){
			throw new IllegalArgumentException("No provider registered with name"+name);
		}else{
			return pro.newService();
		}
	}
}







