package other;
import org.jdesktop.jdic.browser.*;
/**
 * Created by dtdyq on 2017/3/7.
 *   ����JDIC��Browser�ķ������÷���
 */
public class JDICBrowser {
    public static void main(String[] args) throws Exception{
        InternetExplorerEngine  bro=new InternetExplorerEngine ();
        //��ȡ���֣�
        System.out.println(bro.getBrowserName());
        //��ȡ���룺
        System.out.println(bro.getCharsetName());
        //
        System.out.println(bro.getBrowserBinary());
        System.out.println(bro.getEmbeddedBinaryName());
        System.out.println(bro.getFileProtocolURLPrefix());

        System.out.println(bro.isEngineAvailable());
        System.out.println(bro.isInitialized());
        System.out.println(bro.isDefaultBrowser("Internet Explorer"));
        bro.initialize();
    }
}
