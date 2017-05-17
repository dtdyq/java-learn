package java8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 * ӳ��
 *
 */
public class Mapping {
	@Test
	public void TestMap(){
		List<Dish> list = DishManager.list;
		List<String> listName = list.stream()
				.map(Dish::getName)
				.collect(Collectors.toList());
		listName.forEach(System.out::println);
	}
	@Test
	public void TestFlatMap(){
		//flatMap:���������ƽ��Ϊһ����
		String[] words = {"hello","world"};
		List<String> wordStream = Arrays.stream(words)
				.map(word->word.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(Collectors.toList());
		wordStream.forEach(System.out::println);
		
	}

}










