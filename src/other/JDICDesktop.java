package other;
import org.jdesktop.jdic.desktop.Desktop;
import java.net.*;
import java.io.*;
/**
 * Created by dtdyq on 2017/3/7.
 *   测试JDIC的Desktop的静态方法：
 */
public class JDICDesktop {
    public static void main(String[] args){
        try {
            //用系统的默认浏览器打开指定的URL：
            Desktop.browse(new URL("http://www.baidu.com"));

            //用系统中被关联的编辑器打开指定的文件：
            File f=new File("file/other/proDelete.txt");
            //判断文件是否可以被编辑：
            if(Desktop.isEditable(f))
                 Desktop.edit(f);
            //打印文档：
            Desktop.print(f);
            //Desktop的其他静态方法：
            /*
            static boolean	isPrintable(java.io.File file)
                    Tests whether the given file could be printed.
            static void	mail()
                    Launches the message compose window of the default mailer.
            static void	mail(Message msg)
                    Launches the message compose window of the default mailer, and
                    fills in the message fields with the field values of the given
                     Message object.
            static void	open(java.io.File file)
                    Launches the associated application to open a file If the specified
                     file is a directory, the file manager of the current platform is
                      launched to open it.
             */
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
