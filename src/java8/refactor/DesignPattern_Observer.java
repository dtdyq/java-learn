package java8.refactor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * �����ع�֮ʹ��lambdaʵ�������������ģʽ
 * 
 * �۲���ģʽ���������������ĳ���¼�ʱ��ϣ����������֪ͨ�۲�Ķ���
 * ��gui�ж�button�ļ���
 * 
 * eg�����ұ��綩����twitter�����ţ�ϣ�������ǽ��ܵ��������������Ǹ���Ȥ�Ĺؼ���ʱ֪ͨ����
 *
 */
public class DesignPattern_Observer {
	@Test
	public void test(){
		//ʵ�ֹ۲���ģʽ��
		Feed feed = new Feed();
		feed.registerObserver(new NYTimes());
		feed.registerObserver(new Guardian());
		feed.registerObserver(new Lemonde());
		feed.notifyObservers("the queen say her favorite language is java");
		//ʹ��lambda��
		Feed fl = new Feed();
		fl.registerObserver(o->{
			if(!o.isEmpty() && o.contains("dtdyq")){
			System.out.println("wow!,dtdyq is coming! "+o);	
			}
		});
		fl.notifyObservers("dtdyq at tianjin");
	}
}
//�۲��߽ӿڣ�
interface Observer{
	void notify(String tweet);
}
//ʵ�ֲ�ͬ�Ĺ۲��ߣ�
class NYTimes implements Observer{
	@Override
	public void notify(String tweet) {
		if(!tweet.isEmpty() && tweet.contains("money")){
			System.out.println("Breaking news in NY! "+tweet);
		}
	}
}
class Guardian implements Observer{
	@Override
	public void notify(String tweet) {
		if(!tweet.isEmpty() && tweet.contains("queen")){
			System.out.println("Yet another news in london! "+tweet);
		}
	}
}
class Lemonde implements Observer{
	@Override
	public void notify(String tweet) {
		if(!tweet.isEmpty() && tweet.contains("wine")){
			System.out.println("Tody cheese,wine and news! "+tweet);
		}
	}
}
//subject������ӿڣ�
interface Subject{
	void registerObserver(Observer o);
	void notifyObservers(String sweet);
}
//����ʵ�֣�
class Feed implements Subject{
	private final List<Observer> list = new ArrayList<>();

	@Override
	public void registerObserver(Observer o) {
		list.add(o);
	}

	@Override
	public void notifyObservers(String sweet) {
		list.forEach(o->o.notify(sweet));
	}
}
















