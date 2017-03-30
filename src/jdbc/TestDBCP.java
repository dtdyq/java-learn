package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

/**
 * Created by dtdyq on 2017/3/10.
 * ���ݿ�����Ӻ͹ر��Ǽ���ķ�ϵ��Դ�ģ����ʹ�����ݿ����ӳ�������Connection����
 * �������ϵͳ����
 */
public class TestDBCP {
    public static void main(String[] args) throws Exception{
        //�������ݿ���Դ����
        BasicDataSource src=new BasicDataSource();
        //�������ӳ������������
        src.setDriverClassName("com.mysql.jdbc.Driver");
        //�������ݿ����ӳص�URL��
        src.setUrl("jdbc:mysql://127.0.0.1:3306/jdbc");
        //�������ݿ��û�����
        src.setUsername("root");
        //�������ݿ����룺
        src.setPassword("032611");
        //�����������ӳس�ʼ����������
        src.setInitialSize(10);
        //�����������ӳ����ɿ��еĻ��������
        src.setMaxTotal(20);
        //�������ӳ������ж��ٸ��������ӣ�
        src.setMinIdle(3);
        Connection conn=src.getConnection();
        conn.close();

    }

}
