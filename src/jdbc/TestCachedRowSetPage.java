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
 * CachedRowSet会将所有记录装载到内存中，因此数据量过大时可能会发内存溢出
 * CachedRowSet提供了分页功能，可用于解决上述问题
 */
public class TestCachedRowSetPage {
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
    public CachedRowSet query(String sql,int pageSize,int page) throws Exception{
        try(
                Connection conn= DriverManager.getConnection(url,user,password);
                java.sql.Statement sts=conn.createStatement();
                ResultSet set=sts.executeQuery(sql);
                )
        {
            RowSetFactory rf= RowSetProvider.newFactory();
            CachedRowSet cache=rf.createCachedRowSet();
            cache.setPageSize(pageSize);
            cache.populate(set,page);
            return cache;
        }
    }
    public static void main(String[] args)throws Exception{
        TestCachedRowSetPage test=new TestCachedRowSetPage();
        test.initParam("file/jdbc/mysql.ini");
        CachedRowSet set=test.query("select * from course;",3,4);
        while(set.next()){
            System.out.println(set.getString(2)+"\t"+set.getString(5));
        }

    }
}
