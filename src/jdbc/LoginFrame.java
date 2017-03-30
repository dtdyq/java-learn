package jdbc;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/**
 * 
 * @author dtdyq
 *  使用PreparedStatement比Statement更安全，可以防止Sql注入：
 * 
 *	运用用户登录的效果来演示黑客SQL注入
 *	如果用户输入的用户名和密码与数据库中的相符，则登录成功，否则登录失败
 *
 *	总结：
 *		PreparedStatement相对于Statement的好处有：
 *			前者预编译SQL代码，性能更好
 *			前者无需拼接字符串，编程更简单
 *			前者可以防止SQL注入，安全性更高
 */
public class LoginFrame {
	private String filename="file/jdbc/mysql.ini";
	private String driver;
	private String url;
	private String user;
	private String password;
	//主窗口：
	private JFrame jfmain=new JFrame("loginTest"); 
	//用户名输入框：
	private JTextField jtusername=new JTextField(20);
	//密码输入框：
	private JTextField jtpassword=new JTextField(20);
	//登录按钮：
	private JButton jblogin=new JButton("login");
	//取消按钮：
	private JButton jbcancel=new JButton("cancel");
	public void init(){
		Properties pop=new Properties();
		try {
			pop.load(new FileInputStream(filename));
			driver=pop.getProperty("driver");
			url=pop.getProperty("url");
			user=pop.getProperty("user");
			password=pop.getProperty("password");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		jfmain.setLocation(300,300);
		JPanel jp=new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp.add(jtusername);
		jp.add(jtpassword);
		jp.add(jblogin);
		jp.add(jbcancel);
		jfmain.add(jp);
		jfmain.pack();
		jblogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag=testuserPreparedStatement(jtusername.getText(),jtpassword.getText());
				if(flag){
					JOptionPane.showMessageDialog(jfmain, "login success!");
					jtusername.setText("");
					jtpassword.setText("");
				}
				else{
					JOptionPane.showMessageDialog(jfmain, "login false!");
					jtusername.setText("");
					jtpassword.setText("");
				}
			}
			
		});
		jbcancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jfmain.dispose();
			}
		});
		jfmain.setVisible(true);
	}
	//检查用户名和密码是否在数据库中（两种实现方式）：
	@SuppressWarnings("unused")
	private boolean testuser(String username,String pass){
		boolean flag=false;
		Connection conn=null;
		Statement state=null;
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn=DriverManager.getConnection(url,user,password);
			state=conn.createStatement();
			String sql="select * from jdbc_host "
					+"where name='"+username+"' and password='"+pass+"';";
			System.out.println(sql);
			ResultSet set=state.executeQuery(sql);
			if(set.next()){
				flag=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
				state.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}
	private boolean testuserPreparedStatement(String name,String pass){
		boolean flag=false;
		Connection conn=null;
		PreparedStatement state=null;
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn=DriverManager.getConnection(url,user,password);
			state=conn.prepareStatement(
					"select * from jdbc_host where name=? and password=?");
			state.setString(1,name);
			state.setString(2, pass);
			
			ResultSet set=state.executeQuery();
			if(set.next()){
				flag=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
				state.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}
	public static void main(String[] args){
		/*
		 * 输入用户名和密码正确时，可以看到提示窗：login success！
		 * 但是当输入的用户名密码是'or true or'时依然会显示登录成功，即sql注入
		 * 因为此时的查询语句为：
		 * select * from jdbc_user where name=''or true or'' and password='';
		 * select * from jdbc_user where name='' and password=''or true or'';
		 * 可知select 获得的结果总是所有条目
		 * 
		 * 当使用PreparedStatement实现的testuserPreparedStatement()进行验证时，
		 * 用户名和密码将不能通过输入以上语句而登录成功
		 */
		new LoginFrame().init();
	}
}




