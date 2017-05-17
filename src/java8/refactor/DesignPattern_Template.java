package java8.refactor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * �����ع�֮ʹ��lambdaʵ�������������ģʽ
 * 
 * ģ�巽����
 * 
 * eg��ģ���û�������ȡǮ���̣�ͨ���ض��Ĳ�������û������
 *
 */
public class DesignPattern_Template {
	@Test
	public void test(){
		//��ͨʵ���У���Ҫ�̳�OnlineBank�࣬ʵ���Լ���MakeHappy����
		//ͨ��lambda���Ը�����ʵ��ģ�巽����
		//����Ҫд�ض��ĳ��󷽷���ֱ�ӽ�lambda��Ϊ��������
		new OnlineBankLambda().process(2323,
				c->System.out.println("hello "+c.getName()));
	}

}
//�����û�
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
//�洢�����û���Ϣ�����ݿ⣺
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
//ģ���û�ȡǮ��ģ���ࣺ
abstract class OnlineBank{
	public void process(int id){
		User user = DataBase.getById(id);
		MakeHappy(user);
	}
	abstract void MakeHappy(User user);
}
//ʹ��lambda��
class OnlineBankLambda{
	public void process(int id,Consumer<User> c){
		User user = DataBase.getById(id);
		c.accept(user);
	}
}
















