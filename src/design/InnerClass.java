package design;

import org.junit.Test;

public class InnerClass {
	static class Dogs{
		private static Dog dog;
		public Dogs(Dog dog){
			Dogs.dog = dog;
		}
		public Dog getDog() {
			return dog;
		}
		public void setDog(Dog dog) {
			Dogs.dog = dog;
		}
	}
	Dog dog = new Dog("cuty","12");
	Dogs dogs = new Dogs(dog);
	
	@Test
	public void test(){
		InnerClass i1 = new InnerClass();
		InnerClass i2 = new InnerClass();
		System.out.println(i1==i2);
		i1.dogs.getDog().setName("beas");
		System.out.println(i2.dogs.getDog().getName());
		
		InnerClass.Dogs.dog.setName("inng");
		System.out.println(i1.dogs.getDog().getName());
	}
}
class Dog{
	private String name;
	private String age;
	public Dog(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
}
