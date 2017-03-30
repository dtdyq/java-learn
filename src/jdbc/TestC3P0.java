package jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

/**
 * Created by dtdyq on 2017/3/10.
 *   C3P0也是数据库连接池，性能相对要比DBCP好一些;
 *   可以自动清理不再使用的Connection连接和Statement以及ResultSet
 */
public class TestC3P0 {
    public static void main(String[] args)throws Exception{
        ComboPooledDataSource src=new ComboPooledDataSource();
        src.setDriverClass("com.mysql.jdbc.Driver");
        src.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jdbc");
        src.setUser("root");
        src.setPassword("032611");
        //设置连接池的最大连接数：
        src.setMaxPoolSize(20);
        //设置连接池最小连接数：
        src.setMinPoolSize(5);
        //设置数据库连接池初始化连接数：
        src.setInitialPoolSize(10);
        //设置连接池缓存Statement的最大数量：
        src.setMaxStatements(40);
        Connection conn=src.getConnection();
        conn.close();

    }
}
