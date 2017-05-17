package java8.argsFunction;

public class Apple {
	private String color;
	private double weight;
	public Apple(){
		
	}
	public Apple(double weight){
		this.weight = weight;
	}
	public Apple(String color,double weight){
		this.color = color;
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "color: "+this.color+";weight: "+this.weight;
	}
    
}
