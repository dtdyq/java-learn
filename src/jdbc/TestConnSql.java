package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 * @author dtdyq
 * 
 * JDBC数据库编程：
 * 
 * JDBC编程步骤：
 * 1、加载驱动（运用反射）：
 * 		Class.forName(driverClass);
 * 
 * 2、获取数据库的连接：
 * 		connection=DriverManager.getConnection(
 * 							String url,String user,String password);
 * 3、使用Connection对象创建Statement对象：
 * 		.createStatement();
 * 		.prepareStatement(String sql);
 * 		.prepareCall(String sql);
 * 4、使用Statement执行Sql语句：
 * 		.execute();
 * 		.executeUpdate();-->执行DML语句时返回受影响的行数，执行DDL语句时返回0
 * 		.executeQuery();--->只能执行查询语句，返回ResultSet对象
 * 5、操作返回的结果：
 * 6、回收数据库资源：
 */

public class TestConnSql {
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection cnt=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbc","root","032611");

		Statement state=cnt.createStatement();
		
		ResultSet result=state.executeQuery("select * from jdbc_user");

		while(result.next()) {
			System.out.println(
					result.getString(1) + " " + result.getString(2) + " " + result.getString(3)
			);
		}
		cnt.close();
	}
}
