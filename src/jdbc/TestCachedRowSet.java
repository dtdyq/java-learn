package jdbc;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by dtdyq on 2017/3/9.
 *   ����õ�ResultSetʱ�������������̴�������Connection�رպ��ٶ�����ж�ȡ
 *   ��¼�ͻᷢ���쳣�����������ӱ�̸��ӶȺͰ�ȫ�Խ���
 *
 *   ʹ������RowSet���Է�������������
 *   CachedRowSet������������RowSet�ĸ��ӿ�
 */
public class TestCachedRowSet {
     String driver;
     String url;
     String user;
     String password;
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
    public CachedRowSet query(String sql){
        CachedRowSet cache=null;
        try{
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(url,user,password);
            java.sql.Statement sts=conn.createStatement();
            ResultSet set=sts.executeQuery(sql);
            RowSetFactory rf= RowSetProvider.newFactory();
            cache=rf.createCachedRowSet();
            //��ResultSet��װ��RowSet�У�
            cache.populate(set);
            while(cache.next()){
                System.out.println(cache.getString(2));
            }
            conn.close();
            sts.close();
            set.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cache;
    }
    public static void main(String[] args) throws Exception{
        TestCachedRowSet test=new TestCachedRowSet();
        test.initParam("file/jdbc/mysql.ini");
        CachedRowSet set=test.query("select * from jdbc_user");
        while(set.previous()){
            System.out.println(set.getString(1)+"\t"+
            set.getString(3)+"\t"+set.getString(5));
            if(set.getString("name")=="dtdyq"){
                set.updateString("id","1111");
                set.updateRow();
            }
        }
        //���½�������
        Class.forName(test.driver);
        Connection conn=DriverManager.getConnection(test.url,test.user,test.password);
        conn.setAutoCommit(false);
        //��RowSet�������޸�ͬ�������ݿ���
        set.acceptChanges(conn);
    }

}
