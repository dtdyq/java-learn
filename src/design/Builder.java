package design;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ʹ�ù��������������������
 *
 */
public class Builder {
	@Test
	@SuppressWarnings("unused")
	public void test(){
		//frist:
		Food f1 = new Food("meat","jfiew","fiwey","dwoen");
		//second:
		Food2 f2 = new Food2();
		f2.setMeat("fwey");
		f2.setPoder("fewinng");
		f2.setWater("doiwnc");
		f2.setVege("diyeff");
		//third:
		Food3 f3 = new Food3.Builder("fbey","iecbr")
				.water("ejbcw").poder("weocryn").build();
	}
}
//����ͨ��java�ࣺʵ�ֶ����������
@SuppressWarnings("unused")
class Food{
	private final String meat;
	private final String vege;
	
	private final String water;
	private final String poder;
	public Food(String meat) {
		this(meat,null);
	}
	public Food(String meat,String vege){
		this(meat,vege,null);
	}
	public Food(String meat,String vege,String water){
		this(meat,vege,water,null);
	}
	public Food(String meat,String vege,String water,String poder){
		this.meat  =meat;
		this.vege = vege;
		this.water = water;
		this.poder = poder;
	}
	
}
//ʹ��java��getset������
class Food2{
	private String meat;
	private String vege;
	
	private String water;
	private String poder;
	
	public void setMeat(String meat) {
		this.meat = meat;
	}
	public void setVege(String vege) {
		this.vege = vege;
	}
	public void setWater(String water) {
		this.water = water;
	}
	public void setPoder(String poder) {
		this.poder = poder;
	}
	public String getMeat() {
		return meat;
	}
	public String getVege() {
		return vege;
	}
	public String getWater() {
		return water;
	}
	public String getPoder() {
		return poder;
	}
}
//ʹ�ù��������й���
@SuppressWarnings("unused")
class Food3{
	private String meat;
	private String vege;
	
	private String water;
	private String poder;
	public static class Builder{
		private String meat;
		private String vege;
		
		private String water;
		private String poder;
		public Builder(String meat,String vege){
			this.meat = meat;
			this.vege = vege;
		}
		public Builder water(String water){
			this.water = water;
			return this;
		}
		public Builder poder(String poder){
			this.poder = poder;
			return this;
		}
		public Food3 build(){
			return new Food3(this);
		}
	}
	private Food3(Builder builder){
		this.meat = builder.meat;
		this.vege = builder.vege;
		this.water = builder.water;
		this.poder = builder.poder;
	}
}


















