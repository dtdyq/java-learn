package reflect;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.util.Properties;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PacketTooBigException;

/**
 * URLClassLoader是扩展类加载器和系统类加载器的父类
 * 可以从本地文件系统中读取二进制文件来加载类，也可以从远程
 * 主机上获取二进制文件来加载类
 * 
 * URLClassLoader的构造方法：
 * 		URLClassLoader(URL[] urls):使用默认父类加载器创建一个
 * 			ClassLoader对象，并根据指定路径查询并加载类
 * 		URLClassLoader(URL[] urls,ClassLoader parent):使用
 * 			指定父类加载器加载指定的二进制文件
 * @author dtdyq
 *
 */
//通过URLClassLoader来获取数据库连接，不需要将mysql驱动配置到环境变量中
public class TestURLClassLoader {
	private static Connection conn;
	public static Connection getConnection(String url,
	                   String user,String pass)throws Exception{
		if(conn==null){
			//file为前缀，表示从本地文件系统中加载
			URL[] urls={new URL("file:mysql-connector-java-5.1.30-bin.jar")};
			URLClassLoader cl=new URLClassLoader(urls);
			Driver driver=(Driver)cl.loadClass("com.mysql.jdbc.Driver").newInstance();
			Properties pop=new Properties();
			pop.setProperty("user", user);
			pop.setProperty("password",pass);
			conn=driver.connect(url, pop);	
		}
		return conn;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getConnection("jdbc:mysql://localhost:3306/newt", "root", "032611"));
		
	}
}
