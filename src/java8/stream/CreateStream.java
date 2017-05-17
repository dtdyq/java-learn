package java8.stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 
 * @author dtdyq
 * 
 *         构建流
 *
 */
public class CreateStream {
	@Test
	public void createByValue() {
		// 由值创建流
		Stream<String> str = Stream.of("java", "lambda", "function", "parallel");
		str.forEach(System.out::println);
	}

	@Test
	public void createByArr() {
		// 由数组创建流：
		int[] arr = { 3, 4, 8, 1, 5, 9, 5, 8, 3 };
		int sum = Arrays.stream(arr).sum();
		System.out.println(sum);
	}

	@Test
	public void createByFile() throws Exception {
		// 产生一个文件
		StreamFile();
		// 统计共有多少个不同的单词：
		long count = 0;
		try (Stream<String> lines = Files.lines(Paths.get("file/java8/stream.txt"))) {
			count = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
		}
		System.out.println(count);
	}

	// 用于createByFile的工具类，生成一个包含大量随机产生的单词的文本
	public static void StreamFile() throws Exception {
		List<Character> cList = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
		List<String> words = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			StringBuilder sb = new StringBuilder();
			int limit = random.nextInt(12);
			for (int j = 0; j < limit; ++j) {
				sb.append(cList.get(random.nextInt(26)));
			}
			words.add(sb.toString());
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter("file/java8/stream.txt"));
		for (String word : words) {
			bw.write(word + " ");
		}
		bw.close();
	}
	
	@Test
	public void createByFunction(){
		//由函数创建流：
		//iterator:
		Stream.iterate(0, n->n+2)
		.limit(10)
		.forEach(System.out::println);
		//eg:使用iterator生成斐波那契元组序列
		Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[1]+t[0]})
		.limit(20)
		.forEach(e->System.out.println(e[0]+" "+e[1]));
		//generator:接受一个Supplier依次生成新值：
		Stream.generate(Math::random)
		.limit(100)
		.forEach(System.out::println);
		//使用generator生成斐波那契数列：
		IntSupplier is = new IntSupplier(){
			private int pre = 0;
			private int last = 1;
			@Override
			public int getAsInt() {
				int old = this.pre;
				int next = this.pre + this.last;
				this.pre = last;
				this.last = next;
				return old;
			}
			
		};
		IntStream.generate(is)
		.limit(10)
		.forEach(System.out::println);
	}
}










