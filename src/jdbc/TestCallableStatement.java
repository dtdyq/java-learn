package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Properties;

/**
 * 
 * @author dtdyq
 *�洢���ù��̣�CallableStatement��
 */


public class TestCallableStatement {
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
	public void callProcedure(){
		Connection cont=null;
		CallableStatement cs=null;
		try{
			Class.forName(driver);
			cont=DriverManager.getConnection(url,user,password);
			//ʹ��Connection��ȡһ��CallableStatement����
			cs=cont.prepareCall("{call add_pro(?,?,?)}");
			cs.setInt(1, 4);
			cs.setInt(2,5);
			//ע��CallableStatement�ĵ��������������ͣ�
			cs.registerOutParameter(3,Types.INTEGER);
			//ִ�д洢����:
			cs.execute();
			//��ȡ������:
			System.out.println("the third value-->"+cs.getInt(3));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args){
		TestCallableStatement cs=new TestCallableStatement();
		cs.initParam("file/jdbc/mysql.ini");
		cs.callProcedure();
	}
}
