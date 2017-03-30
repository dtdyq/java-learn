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
 *  ʹ��PreparedStatement��Statement����ȫ�����Է�ֹSqlע�룺
 * 
 *	�����û���¼��Ч������ʾ�ڿ�SQLע��
 *	����û�������û��������������ݿ��е���������¼�ɹ��������¼ʧ��
 *
 *	�ܽ᣺
 *		PreparedStatement�����Statement�ĺô��У�
 *			ǰ��Ԥ����SQL���룬���ܸ���
 *			ǰ������ƴ���ַ�������̸���
 *			ǰ�߿��Է�ֹSQLע�룬��ȫ�Ը���
 */
public class LoginFrame {
	private String filename="file/jdbc/mysql.ini";
	private String driver;
	private String url;
	private String user;
	private String password;
	//�����ڣ�
	private JFrame jfmain=new JFrame("loginTest"); 
	//�û��������
	private JTextField jtusername=new JTextField(20);
	//���������
	private JTextField jtpassword=new JTextField(20);
	//��¼��ť��
	private JButton jblogin=new JButton("login");
	//ȡ����ť��
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
	//����û����������Ƿ������ݿ��У�����ʵ�ַ�ʽ����
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
		 * �����û�����������ȷʱ�����Կ�����ʾ����login success��
		 * ���ǵ�������û���������'or true or'ʱ��Ȼ����ʾ��¼�ɹ�����sqlע��
		 * ��Ϊ��ʱ�Ĳ�ѯ���Ϊ��
		 * select * from jdbc_user where name=''or true or'' and password='';
		 * select * from jdbc_user where name='' and password=''or true or'';
		 * ��֪select ��õĽ������������Ŀ
		 * 
		 * ��ʹ��PreparedStatementʵ�ֵ�testuserPreparedStatement()������֤ʱ��
		 * �û��������뽫����ͨ����������������¼�ɹ�
		 */
		new LoginFrame().init();
	}
}




