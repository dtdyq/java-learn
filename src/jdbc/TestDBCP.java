package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

/**
 * Created by dtdyq on 2017/3/10.
 * 数据库的连接和关闭是极其耗费系资源的，因此使用数据库连接池来构造Connection对象，
 * 可以提高系统性能
 */
public class TestDBCP {
    public static void main(String[] args) throws Exception{
        //创建数据库资源对象：
        BasicDataSource src=new BasicDataSource();
        //设置连接池所需的驱动：
        src.setDriverClassName("com.mysql.jdbc.Driver");
        //设置数据库连接池的URL：
        src.setUrl("jdbc:mysql://127.0.0.1:3306/jdbc");
        //设置数据库用户名：
        src.setUsername("root");
        //设置数据库密码：
        src.setPassword("032611");
        //设置数据连接池初始链接数量：
        src.setInitialSize(10);
        //设置数据连接池最多可可有的活动连接数：
        src.setMaxTotal(20);
        //设置连接池最少有多少个空闲链接：
        src.setMinIdle(3);
        Connection conn=src.getConnection();
        conn.close();

    }

}
