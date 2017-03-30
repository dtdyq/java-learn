package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 * @author dtdyq
 * 
 * JDBC���ݿ��̣�
 * 
 * JDBC��̲��裺
 * 1���������������÷��䣩��
 * 		Class.forName(driverClass);
 * 
 * 2����ȡ���ݿ�����ӣ�
 * 		connection=DriverManager.getConnection(
 * 							String url,String user,String password);
 * 3��ʹ��Connection���󴴽�Statement����
 * 		.createStatement();
 * 		.prepareStatement(String sql);
 * 		.prepareCall(String sql);
 * 4��ʹ��Statementִ��Sql��䣺
 * 		.execute();
 * 		.executeUpdate();-->ִ��DML���ʱ������Ӱ���������ִ��DDL���ʱ����0
 * 		.executeQuery();--->ֻ��ִ�в�ѯ��䣬����ResultSet����
 * 5���������صĽ����
 * 6���������ݿ���Դ��
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
