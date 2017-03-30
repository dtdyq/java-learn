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
 *  Blob是二进制长对象，Blob通常用于存储大文件，典型的Blob是一张图片或一个声音文件
 *  由于它们的特殊性，必须使用特殊的方式存储
 *
 *  使用Blob列可以把图片声音等文件的二进制数据保存到数据库中
 *  将Blob数据插入数据库中必须使用PreparedStatement，该对象有方法：
 *      setBinaryStream(int paramIndex,InputStream x)
 *      从ResultSet里取出Blob数据时，要调用ResultSet的getBlob（int columnIndex）
 *
 *      Blob.getBinaryStream()获取该Blob数据的输入流
 *      Blob.getBytes()  获取该对象存储的二进制数据
 */
public class TestBlob {
    JFrame jf=new JFrame("image manager");
    private static Connection conn;
    private static PreparedStatement insert;
    private static PreparedStatement query;
    private static PreparedStatement queryAll;
    private static PreparedStatement delete;
    //定义DefaultListModel对象哪个：
    private DefaultListModel<ImageHolder> imageModel =new DefaultListModel<>();

    private JList<ImageHolder> imagelist=new JList<>(imageModel);
    private JTextField filepath=new JTextField(26);
    private JButton browserBn=new JButton("select");
    private JButton uploanBn=new JButton("upload");
    private JButton deleteBn=new JButton("delete");
    private JLabel imageLabel=new JLabel();
    //以当前路径创建文件选择器：
    JFileChooser chooser=new JFileChooser(".");
    //创建文件过滤器：
   ExtensionFileFilter filter=new ExtensionFileFilter();


    static{
        try{
            Properties props=new Properties();
            props.load(new FileInputStream("file/jdbc/mysql.ini"));
            String driver=props.getProperty("driver");
            String url=props.getProperty("url");
            String name=props.getProperty("user");
            String password=props.getProperty("password");
            //连接数据库：
            Class.forName(driver);
            conn= DriverManager.getConnection(url,name,password);
            //插入后自动生成主键：
            insert=conn.prepareStatement("insert into jdbc_img "+"VALUES (null,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            //创建两个查询对象，分别用于查询指定图片，所有图片：
            query=conn.prepareStatement("SELECT img_data FROM jdbc_img"
                    +" WHERE img_id=?");
            queryAll=conn.prepareStatement("SELECT img_id, img_data FROM jdbc_img");

            //创建删除功能：
            delete=conn.prepareStatement("DELETE FROM jdbc_img WHERE img_id=?");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void init(){
        try{
            //初始化文件选择器：
            filter.addExtension("jpg");
            filter.addExtension("jpeg");
            filter.addExtension("gif");
            filter.addExtension("png");
            filter.setDescription("image file(*.jpg*,*.jpeg*,*.gif*,*.png*...)");
            chooser.addChoosableFileFilter(filter);
            //禁止"文件类型"下拉列表中显示所有文件选项：
            chooser.setAcceptAllFileFilterUsed(false);

            //初始化程序界面：
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
    //查找jdbc_img填充listModel:
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
    //将指定图片放入数据库：
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
    //根据图片ID来显图片：
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
    //自定义方法，用于添加文件扩展名：
    public void addExtension(String extension){
        if(!extension.startsWith("."))
        this.extension.add(".".concat(extension));
    }
    //设置该文件过滤器的描述文本：
    public void setDescription(String ad){
        this.description=ad;
    }
    //判断该文件过滤器是否应该接受该文本：
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
    //返回该文件过滤器的描述文本：
    @Override
    public String getDescription() {
        return description;
    }
}
class ImageHolder{
    //图片的id：
    private int id;
    //图片的名字：
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








