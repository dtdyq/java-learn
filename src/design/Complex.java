package design;
/**
 * 
 * @author dtdyq
 * 
 * ��Ӧ�þ������ֲ��ɱ��ԣ��������ַ�װ
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















