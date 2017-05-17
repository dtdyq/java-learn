package java8.lambda;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 
 * @author dtdyq
 *
 */
@FunctionalInterface
public interface FileProcessor {
	public String process(BufferedReader br)throws IOException;
}
