package other;
import org.jdesktop.jdic.desktop.Desktop;
import java.net.*;
import java.io.*;
/**
 * Created by dtdyq on 2017/3/7.
 *   ����JDIC��Desktop�ľ�̬������
 */
public class JDICDesktop {
    public static void main(String[] args){
        try {
            //��ϵͳ��Ĭ���������ָ����URL��
            Desktop.browse(new URL("http://www.baidu.com"));

            //��ϵͳ�б������ı༭����ָ�����ļ���
            File f=new File("file/other/proDelete.txt");
            //�ж��ļ��Ƿ���Ա��༭��
            if(Desktop.isEditable(f))
                 Desktop.edit(f);
            //��ӡ�ĵ���
            Desktop.print(f);
            //Desktop��������̬������
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
