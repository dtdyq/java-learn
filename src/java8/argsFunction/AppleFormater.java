package java8.argsFunction;
/**
 * 
 * @author dtdyq
 *
 */
@FunctionalInterface
public interface AppleFormater {
	public String accept(Apple apple);
}
class AppleSimpleFormater implements AppleFormater{

	@Override
	public String accept(Apple apple) {
		return "apple's color is "+apple.getColor()+" and weight is "+apple.getWeight();
	}
	
}