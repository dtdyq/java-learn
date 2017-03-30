package jdbc;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.swing.JList;

/**
 * Created by dtdyq on 2017/3/8.
 *  Blob�Ƕ����Ƴ�����Blobͨ�����ڴ洢���ļ������͵�Blob��һ��ͼƬ��һ�������ļ�
 *  �������ǵ������ԣ�����ʹ������ķ�ʽ�洢
 *
 *  ʹ��Blob�п��԰�ͼƬ�������ļ��Ķ��������ݱ��浽���ݿ���
 *  ��Blob���ݲ������ݿ��б���ʹ��PreparedStatement���ö����з�����
 *      setBinaryStream(int paramIndex,InputStream x)
 *      ��ResultSet��ȡ��Blob����ʱ��Ҫ����ResultSet��getBlob��int columnIndex��
 *
 *      Blob.getBinaryStream()��ȡ��Blob���ݵ�������
 *      Blob.getBytes()  ��ȡ�ö���洢�Ķ���������
 */
public class TestBlob {
    JFrame jf=new JFrame("image manager");
    private static Connection conn;
    private static PreparedStatement insert;
    private static PreparedStatement query;
    private static PreparedStatement queryAll;
    private static PreparedStatement delete;
    //����DefaultListModel�����ĸ���
    private DefaultListModel<ImageHolder> imageModel =new DefaultListModel<>();

    private JList<ImageHolder> imagelist=new JList<>(imageModel);
    private JTextField filepath=new JTextField(26);
    private JButton browserBn=new JButton("select");
    private JButton uploanBn=new JButton("upload");
    private JButton deleteBn=new JButton("delete");
    private JLabel imageLabel=new JLabel();
    //�Ե�ǰ·�������ļ�ѡ������
    JFileChooser chooser=new JFileChooser(".");
    //�����ļ���������
   ExtensionFileFilter filter=new ExtensionFileFilter();


    static{
        try{
            Properties props=new Properties();
            props.load(new FileInputStream("file/jdbc/mysql.ini"));
            String driver=props.getProperty("driver");
            String url=props.getProperty("url");
            String name=props.getProperty("user");
            String password=props.getProperty("password");
            //�������ݿ⣺
            Class.forName(driver);
            conn= DriverManager.getConnection(url,name,password);
            //������Զ�����������
            insert=conn.prepareStatement("insert into jdbc_img "+"VALUES (null,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            //����������ѯ���󣬷ֱ����ڲ�ѯָ��ͼƬ������ͼƬ��
            query=conn.prepareStatement("SELECT img_data FROM jdbc_img"
                    +" WHERE img_id=?");
            queryAll=conn.prepareStatement("SELECT img_id, img_data FROM jdbc_img");

            //����ɾ�����ܣ�
            delete=conn.prepareStatement("DELETE FROM jdbc_img WHERE img_id=?");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void init(){
        try{
            //��ʼ���ļ�ѡ������
            filter.addExtension("jpg");
            filter.addExtension("jpeg");
            filter.addExtension("gif");
            filter.addExtension("png");
            filter.setDescription("image file(*.jpg*,*.jpeg*,*.gif*,*.png*...)");
            chooser.addChoosableFileFilter(filter);
            //��ֹ"�ļ�����"�����б�����ʾ�����ļ�ѡ�
            chooser.setAcceptAllFileFilterUsed(false);

            //��ʼ��������棺
            fillListModel();
            filepath.setEditable(false);
          //  imagelist.setSelectionModel();

            JPanel jp=new JPanel();
            jp.add(filepath);
            jp.add(browserBn);
            browserBn.addActionListener(event->{
                int result=chooser.showDialog(jf,"selected");
                if(result==JFileChooser.APPROVE_OPTION){
                    filepath.setText(chooser.getSelectedFile().getPath());
                }
            });

            jp.add(uploanBn);
            uploanBn.addActionListener(event->{
                if(filepath.getText().trim().length()>0){
                    upload(filepath.getText());
                    filepath.setText("");
                }
            });
            jp.add(deleteBn);
            deleteBn.addActionListener(event->{
                try {
                    ImageHolder img = imagelist.getSelectedValue();
                    delete.setInt(1, img.getId());
                    delete.executeUpdate();
                    fillListModel();
                }catch(Exception e){
                    e.printStackTrace();
                }
            });

            JPanel left=new JPanel();
            left.setLayout(new BorderLayout());
            left.add(new JScrollPane(imageLabel),BorderLayout.CENTER);
            left.add(jp,BorderLayout.SOUTH);
            jf.add(left);

            imagelist.setFixedCellWidth(160);
            jf.add(new JScrollPane(imagelist),BorderLayout.EAST);
            imagelist.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount()>=2){
                        ImageHolder cur=imagelist.getSelectedValue();
                        try{
                            showImage(cur.getId());
                        }catch(Exception es){
                            es.printStackTrace();
                        }
                    }
                }
            });
            jf.setSize(700,500);
            jf.setLocation(200,200);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new TestBlob().init();
    }
    //����jdbc_img���listModel:
    public void fillListModel(){
        try {
            ResultSet rs = queryAll.executeQuery();
            imageModel.clear();
            while(rs.next()){
                imageModel.addElement(new ImageHolder(rs.getInt(1),rs.getString(2)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //��ָ��ͼƬ�������ݿ⣺
    public void upload(String filename){
        String imgname=filename.substring(filename.lastIndexOf("\\")+1,filename.lastIndexOf("."));
        File f=new File(filename);
        try{
            InputStream is=new FileInputStream(f);
            insert.setString(1,imgname);
            insert.setBinaryStream(2,is,(int)f.length());

            int affect=insert.executeUpdate();
            if(affect==1){
                fillListModel();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //����ͼƬID����ͼƬ��
    public void showImage(int id){
        try {
            query.setInt(1, id);
            ResultSet rs=query.executeQuery();
            if(rs.next()){
                Blob imgblob=rs.getBlob(1);
                ImageIcon icon=new ImageIcon(imgblob.getBytes(1l,(int)imgblob.length()));
                imageLabel.setIcon(icon);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
class ExtensionFileFilter extends FileFilter{
    private String description="";
    private ArrayList<String> extension =new ArrayList<>();
    //�Զ��巽������������ļ���չ����
    public void addExtension(String extension){
        if(!extension.startsWith("."))
        this.extension.add(".".concat(extension));
    }
    //���ø��ļ��������������ı���
    public void setDescription(String ad){
        this.description=ad;
    }
    //�жϸ��ļ��������Ƿ�Ӧ�ý��ܸ��ı���
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){
            return true;
        }
        String name=f.getName();
        String ext=name.substring(name.lastIndexOf("."));
        if(this.extension.contains(ext)){
            return true;
        }
        return false;
    }
    //���ظ��ļ��������������ı���
    @Override
    public String getDescription() {
        return description;
    }
}
class ImageHolder{
    //ͼƬ��id��
    private int id;
    //ͼƬ�����֣�
    private String name;
    public ImageHolder(int id,String name){
        this.id=id;
        this.name=name;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return name;
    }

}








