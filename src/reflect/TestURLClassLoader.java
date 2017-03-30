package reflect;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.util.Properties;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PacketTooBigException;

/**
 * URLClassLoader����չ���������ϵͳ��������ĸ���
 * ���Դӱ����ļ�ϵͳ�ж�ȡ�������ļ��������࣬Ҳ���Դ�Զ��
 * �����ϻ�ȡ�������ļ���������
 * 
 * URLClassLoader�Ĺ��췽����
 * 		URLClassLoader(URL[] urls):ʹ��Ĭ�ϸ������������һ��
 * 			ClassLoader���󣬲�����ָ��·����ѯ��������
 * 		URLClassLoader(URL[] urls,ClassLoader parent):ʹ��
 * 			ָ���������������ָ���Ķ������ļ�
 * @author dtdyq
 *
 */
//ͨ��URLClassLoader����ȡ���ݿ����ӣ�����Ҫ��mysql�������õ�����������
public class TestURLClassLoader {
	private static Connection conn;
	public static Connection getConnection(String url,
	                   String user,String pass)throws Exception{
		if(conn==null){
			//fileΪǰ׺����ʾ�ӱ����ļ�ϵͳ�м���
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
