package jdbc;


import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
/**
 * Created by dtdyq on 2017/3/9.
 *   JdbcRowSet ��һ�����޸ĵĿɹ����Ľ������
 *   JdbcRowSetImpl �������ڴ���JdbcRowSet��ʵ��
 *
 *   ������
 *      ����ֱ����JdbcRowSetImplʵ������ϣ������ں��ڵ�ά��������
 *      JdbcRowSetImpl����һ��������API�����ڿ��ܱ�ɾ��
 *
 */
public class TestJdbcRowSet {
    private String driver;
    private String url;
    private String user;
    private String password;
    public void initParam(String fileName){
        try {
            Properties pop = new Properties();
            pop.load(new FileInputStream(fileName));
            driver=pop.getProperty("driver");
            url=pop.getProperty("url");
            user=pop.getProperty("user");
            password=pop.getProperty("password");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void update(String sql){
        try{
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(url,user,password);
            JdbcRowSet jdbcrs=new JdbcRowSetImpl(conn);
            jdbcrs.setCommand(sql);
            jdbcrs.execute();
            jdbcrs.afterLast();
            while(jdbcrs.previous()){
                System.out.println(jdbcrs.getString(1)+"\t"
                +jdbcrs.getString(2)+"\t"+jdbcrs.getString(3));
                if(jdbcrs.getInt("id")==1111){
                    jdbcrs.updateString("name","XXX");
                    jdbcrs.updateRow();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        TestJdbcRowSet set=new TestJdbcRowSet();
        set.initParam("file/jdbc/mysql.ini");
        set.update("select * from jdbc_user");

    }
}
