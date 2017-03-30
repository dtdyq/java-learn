package jdbc;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by dtdyq on 2017/3/8.
 * 管理结果集：
 *      默认打开的ResultSet是不可更新的，如果要创建可更新的ResultSet，在
 *      创建Statement或PreparedStatement时必须指定额外的参数：
 *      ResultSetType：控制ResultSet的类型：
 *          ResultSet.TYPE_FORWARD_ONLY   :该常量指定ResultSet只能向前移动
 *          ResultSet.TYPE_SCROLL_INSENSITIVE: 该常量可控制记录指针自由移动，但底层数据的
 *          改变不会影响ResultSet的内容
 *          ResultSet.TYPE_SCROLL_SENSITIVE:与上一条相同，但底层数据的改变会影响
 *          ResultSet的内容
 *      ResultSetConcurrency：控制ResultSet的并发类型：
 *          ResultSet.CONCUR_READ_ONLY :该常量指示ResultSet是可读的并发模式
 *          ResultSet.CONCUR_UPDATABLE: 该常量指示ResultSet是可更新的并发模式
 *
 *      程序可调用ResultSet的updateXxx(int columnIndex,Xxx value)
 *      来修改记录指针所指定的特定的列和行的值
 *      通过updateRow()来提交修改
 *
 */
public class TestResultSet {
    private String driver;
    private String url;
    private String user;
    private String password;
    public void initParm(String file){
        try {
            Properties pop = new Properties();
            pop.load(new FileInputStream(file));
            driver=pop.getProperty("driver");
            url=pop.getProperty("url");
            user=pop.getProperty("user");
            password=pop.getProperty("password");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void query(String sql){
        try {
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(url,user,password);
            PreparedStatement pst=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet set=pst.executeQuery();
            set.last();
            int rows=set.getRow();
            for(int i=rows;i>0;i--){
                set.absolute(i);
                System.out.println(set.getString(1)+"\t"+set.getString(2)+"\t"+set.getString(3));
                set.updateString(2,set.getString(2).substring(0,set.getString(2).length()-1));
                set.updateRow();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        TestResultSet trs=new TestResultSet();
        trs.initParm("file/jdbc/mysql.ini");
        trs.query("select * from jdbc_user");
    }
}
