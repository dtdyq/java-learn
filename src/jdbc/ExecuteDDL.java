package jdbc;

import java.sql.*;
import java.util.Properties;
import java.io.*;
/**
 * 
 * @author dtdyq
 *实现创建表格操作：
 */
public class ExecuteDDL {
	private String driver;
	private String url;
	private String user;
	private String password;
	public void initParam(String filename){
		//使用Properties来加载属性文件：
		Properties pop=new Properties();
		try{
			FileInputStream file=new FileInputStream(filename);
			pop.load(file);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		driver=pop.getProperty("driver");
		url=pop.getProperty("url");
		user=pop.getProperty("user");
		password=pop.getProperty("password");
	}
	public void createTable(String sql){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connect;
		try {
			connect = DriverManager.getConnection(url,user,password);
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		Statement state;
		try {
			state=connect.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
		try {
			//执行的是DDL语句，返回0
			state.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
				
	}
	public static void main(String[] args){
		ExecuteDDL exe=new ExecuteDDL();
		exe.initParam("file/jdbc/mysql.ini");
		exe.createTable("create table jdbc_DDL(" +
				"jdbc_id int auto_increment primary key,"+
				"jdbc_name varchar(255),"+
				"jdbc_type varchar(255));"
				);
		System.out.println("create table successfully");
	}
}
