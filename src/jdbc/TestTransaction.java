package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;
import java.io.FileNotFoundException;

/**
 * Created by dtdyq on 2017/3/10.
 *   事务由一组DML语句、一个DDl语句、一个DCL语句组成，是保证底层数据完整性的重要手段
 *   特性：
 *      原子性：不可分割，要么全部执行，要么不执行
 *      一致性：事务执行的结果，必须使数据库从一个一致性状态变到另一个状态
 *      隔离性：各个事务的执行互不干扰
 *      持续性：对数据库所做的任何改变都应该记录到永久存储器中
 *
 *  begin可表示临时开始一次事务
 *  可以使用rollback回滚事务，savepoint a设置回滚点，并用rollback to a回到回滚点
 *
 *
 *
 *  JDBC提供了事务支持，Connection默认自动提交
 *  可以使用setAutoCommit(false);来关闭自动提交
 *  使用getAutoCommit()；来获得自动提交
 *
 *  关闭自动提交后
 *  执行的DML语句不会立刻生效，只有调用Commit方法才能完成操作
 *  也可以使用rollback()回滚事务，使上述操作无效
 *
 *  java8新增了批量更新，可将多条SQL语句同时收集并提交
 */
public class TestTransaction {
    private String driver;
    private String url;
    private String user;
    private String password;
    public void initParam(String filename){
        try{
            Properties pop=new Properties();
            pop.load(new FileInputStream(filename));
            driver=pop.getProperty("driver");
            url=pop.getProperty("url");
            user=pop.getProperty("user");
            password= pop.getProperty("password");

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException ee){
            ee.printStackTrace();
        }
    }
    public void InsertTransaction(String[] sqls){
        Connection conn=null;
        Savepoint sp=null;
        Statement ps=null;
        try{
            Class.forName(driver);
            conn= DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);
            ps=conn.createStatement();
            ps.executeUpdate(sqls[0]);

            for(int i=1;i<4;i++){
                ps.executeUpdate(sqls[i]);
                sp=conn.setSavepoint();
            }
            conn.commit();
        }catch(Exception ee){
            try {
                conn.rollback(sp);
                conn.commit();
                ee.printStackTrace();
                conn.close();
                ps.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        TestTransaction test=new TestTransaction();
        test.initParam("file/jdbc/mysql.ini");
        String[] sql={"insert into jdbc_test values( 12,'sql','mysql')",
                "insert into jdbc_test values( 16,'mapreduce','database')",
                "insert into jdbc_test values( 56,'redis','test')",
                "insert into jdbc_test values( null,'mongodb','ddddd')",
                };
        //插入语句的第四条主键为空，非法，因此程序非正常结束，事务自动回滚
        test.InsertTransaction(sql);
    }
}

