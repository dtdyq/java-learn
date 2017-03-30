package jdbc;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by dtdyq on 2017/3/9.
 * java7后提供了RowSetProvider来创建RowSetFactory
 * 再用RowSetFactory来创建
 *      CachedRowSet
 *      FilteredRowSet
 *      JdbcRowSet
 *      JoinRowSet
 *      WebRowSet
 */
public class TestRowSetFactory {
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
            JdbcRowSet jrs= RowSetProvider.newFactory().createJdbcRowSet();
            jrs.setUrl(url);
            jrs.setUsername(user);
            jrs.setPassword(password);
            jrs.setCommand(sql);
            jrs.execute();
            while(jrs.next()){
                System.out.println(jrs.getString(1)+"\t"
                        +jrs.getString(2)+"\t"+jrs.getString(3));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        TestRowSetFactory test=new TestRowSetFactory();
        test.initParam("file/jdbc/mysql.ini");
        test.update("select * from jdbc_user");
    }
}
