package java8.argsFunction;
/**
 * 
 * @author dtdyq
 *
 */
@FunctionalInterface
public interface ApplePredicate {
	boolean test (Apple apple);
}
//根据苹果的不同属性实现不同的选择标准：
class AppleWeightPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return apple.getWeight()>100;
	}
}
class AppleGreenPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return apple.getColor().equals("green");
	}
}
