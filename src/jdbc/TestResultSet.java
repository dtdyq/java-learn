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
 * ����������
 *      Ĭ�ϴ򿪵�ResultSet�ǲ��ɸ��µģ����Ҫ�����ɸ��µ�ResultSet����
 *      ����Statement��PreparedStatementʱ����ָ������Ĳ�����
 *      ResultSetType������ResultSet�����ͣ�
 *          ResultSet.TYPE_FORWARD_ONLY   :�ó���ָ��ResultSetֻ����ǰ�ƶ�
 *          ResultSet.TYPE_SCROLL_INSENSITIVE: �ó����ɿ��Ƽ�¼ָ�������ƶ������ײ����ݵ�
 *          �ı䲻��Ӱ��ResultSet������
 *          ResultSet.TYPE_SCROLL_SENSITIVE:����һ����ͬ�����ײ����ݵĸı��Ӱ��
 *          ResultSet������
 *      ResultSetConcurrency������ResultSet�Ĳ������ͣ�
 *          ResultSet.CONCUR_READ_ONLY :�ó���ָʾResultSet�ǿɶ��Ĳ���ģʽ
 *          ResultSet.CONCUR_UPDATABLE: �ó���ָʾResultSet�ǿɸ��µĲ���ģʽ
 *
 *      ����ɵ���ResultSet��updateXxx(int columnIndex,Xxx value)
 *      ���޸ļ�¼ָ����ָ�����ض����к��е�ֵ
 *      ͨ��updateRow()���ύ�޸�
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
