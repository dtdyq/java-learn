package jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.*;
import java.util.*;

/**
 * Created by dtdyq on 2017/3/9.
 *  ResultSetMetaData:���������ResultSet��getMetaData()��ã�
 *  ����ͨ���������й�ResultSet�ĸ�����Ϣ������������ÿ�е����ֺ���������
 *
 *  ����ResultSetMetaData��ϵͳ�����Ƚϴ��ڲ���Ҫ�õĵط�Ӧ�ü���ʹ�á�
 */
public class TestResultSetMetaData {
    JFrame jf=new JFrame("sql query");
    private JScrollPane scrollPane;
    private JButton execBn=new JButton("query");

    //���������ѯ�����ı���
    private JTextField sqlField=new JTextField(45);
    private static Connection conn;
    private static Statement stmt;
    static{
        try{
            Properties props=new Properties();
            props.load(new FileInputStream("file/jdbc/mysql.ini"));

            String driver=props.getProperty("driver");
            String url=props.getProperty("url");
            String user=props.getProperty("user");
            String password=props.getProperty("password");

            Class.forName(driver);
            conn= DriverManager.getConnection(url,user,password);
            stmt=conn.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void init(){
        JPanel top=new JPanel();
        top.add(new JLabel("input query lang:"));
        top.add(sqlField);
        top.add(execBn);

        sqlField.addActionListener(new ExecListener());
        execBn.addActionListener(new ExecListener());

        jf.add(top, BorderLayout.NORTH);
        jf.setBounds(200,200,700,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    class ExecListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(scrollPane!=null){
                jf.remove(scrollPane);
            }
            try{
                ResultSet rs=stmt.executeQuery(sqlField.getText());
                ResultSetMetaData remdd=rs.getMetaData();
                Vector<String> columnName=new Vector<>();
                Vector<Vector<String>> data=new Vector<>();
                for(int i=0;i<remdd.getColumnCount();i++){
                    columnName.add(remdd.getColumnName(i+1));
                }
                while(rs.next()){
                    Vector<String> v=new Vector<>();
                    for(int i=0;i<remdd.getColumnCount();i++){
                        v.add(rs.getString(i+1));
                    }
                    data.add(v);
                }
                JTable table=new JTable(data,columnName);
                scrollPane=new JScrollPane(table);
                jf.add(scrollPane);
                jf.validate();
            }catch(Exception ee){
                scrollPane=new JScrollPane(
                        new JLabel(
                                "<html><h1 style='color:red'>" +
                                        "query language is illegal!</h1></hmtl>"));
                jf.add(scrollPane);
                jf.validate();
            }
        }
    }
    public static void main(String[] args){
        new TestResultSetMetaData().init();
    }
}