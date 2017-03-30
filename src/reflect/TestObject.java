package reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
/**
 * 通过反射来操作对象：
 * 		newInstance():该方法会使用默认构造器来创建对象的实例
 * 		获取Constructor对象，再指定特定的参数来产生对象的实例
 * @author dtdyq
 *
 */
public class TestObject {
	private HashMap<String,Object> objPool=new HashMap<>();
	private Object createObject(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> cla=Class.forName(name);
		return cla.newInstance();
	}
	public void initPool(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		Properties pop=new Properties();
		pop.load(new FileInputStream(fileName));
		for(String str:pop.stringPropertyNames()){
			objPool.put(str, createObject(pop.getProperty(str)));
		}
	}
	public Object getObject(String name){
		return objPool.get(name);
	}
	public static void main(String[] args) throws Exception {
		TestObject to=new TestObject();
		to.initPool("file/reflect/object.txt");
		System.out.println(to.getObject("date"));
		System.out.println(to.getObject("jframe"));
		
	}
}
