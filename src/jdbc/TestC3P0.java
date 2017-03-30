package jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

/**
 * Created by dtdyq on 2017/3/10.
 *   C3P0Ҳ�����ݿ����ӳأ��������Ҫ��DBCP��һЩ;
 *   �����Զ�������ʹ�õ�Connection���Ӻ�Statement�Լ�ResultSet
 */
public class TestC3P0 {
    public static void main(String[] args)throws Exception{
        ComboPooledDataSource src=new ComboPooledDataSource();
        src.setDriverClass("com.mysql.jdbc.Driver");
        src.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jdbc");
        src.setUser("root");
        src.setPassword("032611");
        //�������ӳص������������
        src.setMaxPoolSize(20);
        //�������ӳ���С��������
        src.setMinPoolSize(5);
        //�������ݿ����ӳس�ʼ����������
        src.setInitialPoolSize(10);
        //�������ӳػ���Statement�����������
        src.setMaxStatements(40);
        Connection conn=src.getConnection();
        conn.close();

    }
}
