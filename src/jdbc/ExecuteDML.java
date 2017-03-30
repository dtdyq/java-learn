package jdbc;
import java.util.*;
import java.sql.*;
import java.io.*;
/**
 * 
 * @author dtdyq
 *执行mysql插入操作：
 */
public class ExecuteDML {
	private String driver;
	private String url;
	private String user;
	private String password;
	public void initParam(String filename){
		Properties pop=new Properties();
		try{
			pop.load(new FileInputStream(filename));
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		driver=pop.getProperty("driver");
		url=pop.getProperty("url");
		user=pop.getProperty("user");
		password=pop.getProperty("password");
	}
	public int insertTable(String sql){
		int result;
		try{
			Class.forName(driver);
			
			Connection conn=DriverManager.getConnection(url,user,password);
			Statement state=conn.createStatement();
			//执行DML语句，返回被影响的行数：
			result=state.executeUpdate(sql);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}
	public static void main(String[] agrs){
		ExecuteDML exe=new ExecuteDML();
		exe.initParam("file/jdbc/mysql.ini");
		int result=exe.insertTable("insert into jdbc_user "+
				"values(7346,\"dwuqi\",24,1,\"xinshou\");"
				);
		System.out.println("insert successful: "+result);
	}
}





