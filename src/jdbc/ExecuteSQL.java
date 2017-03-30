package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author dtdyq
 *使用execute方法执行SQL语句：
 *		该方法可以执行所有的SQL语句，但使用时相对繁琐
 *		返回值为boolean 是否返回了ResultSet对象
 *		使用getResultSet()和getUpdateCount()获得返回结果集合和受影响的行数
 */

public class ExecuteSQL {
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
	public void executeSQL(String sql){
		boolean hasResult;
		Statement state;
		try{
			
			Class.forName(driver);
			
			Connection conn=DriverManager.getConnection(url,user,password);
			state=conn.createStatement();
			//执行SQL语句，返回布尔值：
			hasResult=state.execute(sql);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		//如果存在结果集，则打印出出所有结果，否则输出受语句影响的条数：
		if(hasResult){
			try {
				//获取结果集：
				ResultSet set=state.getResultSet();
				//ResultSetMetaData是用于分析结果集的元数据接口：
				ResultSetMetaData rsmd=set.getMetaData();
				int columncount=rsmd.getColumnCount();
				while(set.next()){
					for(int i=0;i<columncount;i++){
						System.out.print(set.getString(i+1)+"\t");
					}
					System.out.println();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				System.out.println("受该Sql语句影响的记录共： "+state.getUpdateCount()+"条");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){

		ExecuteSQL exe=new ExecuteSQL();
		
		exe.initParam("file/jdbc/mysql.ini");
		//删除表格语句：
		exe.executeSQL("drop table  if exists jdbc_lang");
		//创建表格语句：
		exe.executeSQL("create table if not exists jdbc_lang("+
				"name varchar(255),type varchar(255),comment varchar(255),users int);"
				);
		//执行插入语句：
		exe.executeSQL("insert into jdbc_lang "+
				"values(\"java\",\"oop\",\"great\",543);"
				);
				
		//执行查询语句操作：
		exe.executeSQL("select * from jdbc_lang");
	}
}





