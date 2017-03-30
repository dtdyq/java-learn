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
 *ʹ��execute����ִ��SQL��䣺
 *		�÷�������ִ�����е�SQL��䣬��ʹ��ʱ��Է���
 *		����ֵΪboolean �Ƿ񷵻���ResultSet����
 *		ʹ��getResultSet()��getUpdateCount()��÷��ؽ�����Ϻ���Ӱ�������
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
			//ִ��SQL��䣬���ز���ֵ��
			hasResult=state.execute(sql);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		//������ڽ���������ӡ�������н����������������Ӱ���������
		if(hasResult){
			try {
				//��ȡ�������
				ResultSet set=state.getResultSet();
				//ResultSetMetaData�����ڷ����������Ԫ���ݽӿڣ�
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
				System.out.println("�ܸ�Sql���Ӱ��ļ�¼���� "+state.getUpdateCount()+"��");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){

		ExecuteSQL exe=new ExecuteSQL();
		
		exe.initParam("file/jdbc/mysql.ini");
		//ɾ�������䣺
		exe.executeSQL("drop table  if exists jdbc_lang");
		//���������䣺
		exe.executeSQL("create table if not exists jdbc_lang("+
				"name varchar(255),type varchar(255),comment varchar(255),users int);"
				);
		//ִ�в�����䣺
		exe.executeSQL("insert into jdbc_lang "+
				"values(\"java\",\"oop\",\"great\",543);"
				);
				
		//ִ�в�ѯ��������
		exe.executeSQL("select * from jdbc_lang");
	}
}





