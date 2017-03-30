package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
import java.io.*;

import com.mysql.jdbc.PreparedStatement;
/**
 * 
 * @author dtdyq
 *PreparedStatement:
 *		是Statement的子接口
 *		可以预编译SQL语句，预编译的SQL语句被存储在PreparedStatement对象中
 *		比Statement更高效
 */
public class TestPreparedStatement {
	private String driver;
	private String url;
	private String user;
	private String password;
	public void initParam(String filename){
		Properties pop=new Properties();
		try{
			pop.load(new FileInputStream(filename));
			driver=pop.getProperty("driver");
			url=pop.getProperty("url");
			user=pop.getProperty("user");
			password=pop.getProperty("password");
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public void insertStatement(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		long t=System.currentTimeMillis();
		try{
			Connection conn=DriverManager.getConnection(url,user,password);
			Statement state=conn.createStatement();
			for(int i=0;i<100;i++){
				state.executeUpdate("insert into jdbc_test "+
						"values("+"\"java\","+"\"oop\","+i+");"
						);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		System.out.println("use Statement:"+(System.currentTimeMillis()-t));
	}
	public void insertProparedStatement(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		long t=System.currentTimeMillis();
		try{
			Connection conn=DriverManager.getConnection(url,user,password);
			//预编译：
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement("" +
					"insert into jdbc_test values(?,?,?);");
			for(int i=0;i<100;i++){
				//插入元素：
				ps.setString(1, "C++");
				ps.setString(2, "oop");
				ps.setInt(3,i+111);
				ps.executeUpdate();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		System.out.println("use PreparedStatement: "+(System.currentTimeMillis()-t));
	}
	public static void main(String[] args){
		//比较Statement和PreparedStatement的执行效率：
		TestPreparedStatement pst=new TestPreparedStatement();
		pst.initParam("file/jdbc/mysql.ini");
		pst.insertStatement();
		pst.insertProparedStatement();
		//由结果可知PreparedStatement执行速度大概在Statement的2-3倍左右
	}
}
