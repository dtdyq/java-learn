package java8.refactor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 代码重构之使用lambda实现面向对象的设计模式
 * 
 * 模板方法：
 * 
 * eg：模拟用户在银行取钱过程，通过特定的操作提高用户满意度
 *
 */
public class DesignPattern_Template {
	@Test
	public void test(){
		//普通实现中，需要继承OnlineBank类，实现自己的MakeHappy方法
		//通过lambda可以更简洁地实现模板方法：
		//不需要写特定的抽象方法，直接将lambda作为参数传入
		new OnlineBankLambda().process(2323,
				c->System.out.println("hello "+c.getName()));
	}

}
//银行用户
class User{
	private int id;
	private String name;
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
//存储银行用户信息的数据库：
class DataBase{
	static List<User> users;
	static{
		users = Arrays.asList(
				new User(2323,"weo"),
				new User(8756,"ebew"),
				new User(7553,"qngb")
				);
	}
	public static User getById(int id){
		return users.stream()
				.filter(e->e.getId()==id)
				.findFirst().orElse(null);
	}
}
//模拟用户取钱的模板类：
abstract class OnlineBank{
	public void process(int id){
		User user = DataBase.getById(id);
		MakeHappy(user);
	}
	abstract void MakeHappy(User user);
}
//使用lambda：
class OnlineBankLambda{
	public void process(int id,Consumer<User> c){
		User user = DataBase.getById(id);
		c.accept(user);
	}
}
















