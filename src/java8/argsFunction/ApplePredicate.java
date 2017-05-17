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
//����ƻ���Ĳ�ͬ����ʵ�ֲ�ͬ��ѡ���׼��
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
