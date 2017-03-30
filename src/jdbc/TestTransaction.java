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
 *   ������һ��DML��䡢һ��DDl��䡢һ��DCL�����ɣ��Ǳ�֤�ײ����������Ե���Ҫ�ֶ�
 *   ���ԣ�
 *      ԭ���ԣ����ɷָҪôȫ��ִ�У�Ҫô��ִ��
 *      һ���ԣ�����ִ�еĽ��������ʹ���ݿ��һ��һ����״̬�䵽��һ��״̬
 *      �����ԣ����������ִ�л�������
 *      �����ԣ������ݿ��������κθı䶼Ӧ�ü�¼�����ô洢����
 *
 *  begin�ɱ�ʾ��ʱ��ʼһ������
 *  ����ʹ��rollback�ع�����savepoint a���ûع��㣬����rollback to a�ص��ع���
 *
 *
 *
 *  JDBC�ṩ������֧�֣�ConnectionĬ���Զ��ύ
 *  ����ʹ��setAutoCommit(false);���ر��Զ��ύ
 *  ʹ��getAutoCommit()��������Զ��ύ
 *
 *  �ر��Զ��ύ��
 *  ִ�е�DML��䲻��������Ч��ֻ�е���Commit����������ɲ���
 *  Ҳ����ʹ��rollback()�ع�����ʹ����������Ч
 *
 *  java8�������������£��ɽ�����SQL���ͬʱ�ռ����ύ
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
        //�������ĵ���������Ϊ�գ��Ƿ�����˳�������������������Զ��ع�
        test.InsertTransaction(sql);
    }
}

