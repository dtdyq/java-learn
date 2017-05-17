package java8.functional;

import java.util.*;
/**
 * 
 * @author dtdyq
 *
 */
public class TestParallel {
	public static void main(String[] args){
		List<Integer> initList=Arrays.asList(1,2,3,4,5);
		OptionalDouble d=initList
				.parallelStream()
				.map(i->new Point(i%3,i/3))
				.mapToDouble(p->p.distance(0, 0))
				.max();
		System.out.println(d.getAsDouble());
	}
}
class Point{
	private double x;
	private double y;
	Point(double x,double y){
		this.x=x;
		this.y=y;
	}
	double distance(double x,double y){
	return (this.x-x)*(this.y-y);	
	}
}
