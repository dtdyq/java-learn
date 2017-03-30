package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by dtdyq on 2017/3/10.
 * ����DatabaseMetaData�������ݿ���Ϣ
 */
public class TestDatabaseMetaData {
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
    public void info(){
        try{
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(url,user,password);
            DatabaseMetaData dm=conn.getMetaData();

            //��ȡmysql֧�ֵ����б����ͣ�
            ResultSet sett=dm.getTableTypes();
//            System.out.println("====mysql tables====");
//            printResultSet(sett);

            //��ȡ��ǰ���ݿ��ȫ�����ݱ�
            sett=dm.getTables(null,null,"%",new String[]{});
            System.out.println("====mysql's tables:");
            printResultSet(sett);
//
//            //��ȡCourse���������
//            sett=dm.getPrimaryKeys(null,null,"course");
//            System.out.println("====mysql course's primary key====");
//            printResultSet(sett);
//
//            //��ȡ��ǰ���ݿ��ȫ���洢����;
//            sett=dm.getProcedures(null,null,"%");
//            System.out.println("====mysql procedure====");
//            printResultSet(sett);
//
//            //��ȡcourse��ȫ�������֣�
//            sett=dm.getColumns(null,null,"course","%");
//            System.out.println("====course data====");
//            printResultSet(sett);

            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void printResultSet(ResultSet set) throws Exception{
        ResultSetMetaData data=set.getMetaData();
        int columns=data.getColumnCount();
        for(int i=0;i<columns;i++){
            System.out.print(data.getColumnName(i+1)+"\t");
        }
        System.out.println();
        while(set.next()){
            for(int i=0;i<columns;i++){
                System.out.print(set.getString(i+1)+"\t");
            }
            System.out.println();
        }
        set.close();
    }
    public static void main(String[] args){
        TestDatabaseMetaData test=new TestDatabaseMetaData();
        test.initParam("file/jdbc/mysql.ini");
        test.info();
    }
}
