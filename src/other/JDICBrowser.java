package other;
import org.jdesktop.jdic.browser.*;
/**
 * Created by dtdyq on 2017/3/7.
 *   测试JDIC的Browser的方法和用法：
 */
public class JDICBrowser {
    public static void main(String[] args) throws Exception{
        InternetExplorerEngine  bro=new InternetExplorerEngine ();
        //获取名字：
        System.out.println(bro.getBrowserName());
        //获取编码：
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
