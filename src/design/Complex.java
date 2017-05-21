package design;
/**
 * 
 * @author dtdyq
 * 
 * 类应该尽量保持不可变性，尽量保持封装
 *
 */
public class Complex {
	private final double real;
	private final double img;
	private Complex(double real,double img){
		this.real = real;
		this.img = img;
	}
	public static Complex valueOf(double real,double img){
		return new Complex(real,img);
	}
	public Complex add(Complex c){
		return valueOf(this.real+c.real,this.img+c.img);
	}
	public Complex minux(Complex c){
		return valueOf(this.real-c.real,this.img-c.img);
	}
}















