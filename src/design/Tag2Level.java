package design;

import org.junit.Assert;
import org.junit.Test;


/**
 * 
 * @author dtdyq
 * 
 * 优先使用层次关系而非标签类
 *
 */
public class Tag2Level {
	@Test
	public void test(){
		Figure f1 = new Figure(Figure.Shape.Cycle,12.3);
		Assert.assertTrue(Math.PI*Math.pow(12.3, 2)==f1.area());
		Square sq = new Square(11.2);
		System.out.println(sq.area());
	}
}
class Figure{
	public enum Shape{Cycle,Rectangle}  
	private final Shape shape;
	private double height;
	private double width;
	private double radius;
	public Figure(Shape shape,double radius){
		this.shape = shape;
		this.radius = radius;
	}
	public Figure(Shape shape,double height,double width){
		this.shape  =shape;
		this.width = width;
		this.height = height;
	}
	public double area(){
		if(shape.equals(Shape.Cycle)){
			return Math.PI*Math.pow(radius, 2);
		}else{
			return height*width;
		}
	}
}
//上面的类使用了标签，效率低，代码冗余，差劲！
abstract class Figures{
	public abstract double area();
}
class Cycle extends Figures{
	private final double radius;
	public Cycle(double radius){
		this.radius = radius;
	}
	@Override
	public double area() {
		return Math.PI*Math.pow(radius, 2);
	}
}
class Rectangle extends Figures{
	private final double height;
	private final double width;
	public Rectangle(double height,double width){
		this.height = height;
		this.width = width;
	}
	@Override
	public double area() {
		return width*height;
	}
}
//使用层次关系的类很容易进行扩展：
class Square extends Rectangle{
	public Square(double side) {
		super(side, side);
	}
}
















