package java8.refactor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * 代码重构之使用lambda实现面向对象的设计模式
 * 
 * 观察者模式：当主题对象发生了某个事件时，希望能主动的通知观察的对象
 * 如gui中对button的监听
 * 
 * eg：几家报社订阅了twitter的新闻，希望在它们接受到的新闻中有它们感兴趣的关键字时通知它们
 *
 */
public class DesignPattern_Observer {
	@Test
	public void test(){
		//实现观察者模式：
		Feed feed = new Feed();
		feed.registerObserver(new NYTimes());
		feed.registerObserver(new Guardian());
		feed.registerObserver(new Lemonde());
		feed.notifyObservers("the queen say her favorite language is java");
		//使用lambda：
		Feed fl = new Feed();
		fl.registerObserver(o->{
			if(!o.isEmpty() && o.contains("dtdyq")){
			System.out.println("wow!,dtdyq is coming! "+o);	
			}
		});
		fl.notifyObservers("dtdyq at tianjin");
	}
}
//观察者接口：
interface Observer{
	void notify(String tweet);
}
//实现不同的观察者：
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
//subject：主题接口：
interface Subject{
	void registerObserver(Observer o);
	void notifyObservers(String sweet);
}
//主题实现：
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
















