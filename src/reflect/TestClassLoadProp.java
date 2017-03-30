package reflect;

import java.net.URL;
import java.util.Enumeration;

/**
 * Created by dtdyq on 2017/3/13.
 *  类加载机制：
 *      全盘负责：当一个类加载器加载某个class时，该class所依赖和引用的其他class
 *               也将由该类加载器负责载入
 *      父类委托：先让父类加载器试图加载该class，只有在父类加载器无法无法加载该
 *               类时，才会尝试从自己的类路径进行加载
 *      缓存机制：保证所有加载过的class都会被缓存，当程序需要使用某个class时，类加载器
 *               首先从缓存区中搜索该class，只有在缓存区中找不到该类时，系统才会读取
 *               该类的二进制数据，并将其转化为class对象，存入缓冲区中
 *  用户也可以通过继承ClassLoader来实现自定义的类加载器
 *
 *
 *  类加载步骤：
 *      1、检测此class是否被加载过，有则直接到第八步；
 *      2、如果父类加载器不存在，则到第四步执行
 *      3、请求使用父类加载器载入目标类，如果成功则到第八步，否则第五步
 *      4、请求根类加载器加载目标类成功则到第八步，否则到第七步
 *      5、当前类加载器寻找class文件，如果找到则继续，否则到第七步
 *      6、从文件中载入class，成功载入后到第八步
 *      7、抛出ClassNotFoundException异常
 *      8、返回对应的class对象
 */
public class TestClassLoadProp {
    public static void main(String[] args){
        try{
            ClassLoader systemloader=ClassLoader.getSystemClassLoader();
            System.out.println("system classloader:"+systemloader);

            Enumeration<URL> eml=systemloader.getResources("");
            while(eml.hasMoreElements()){
                System.out.println(eml.nextElement());
            }
            //获取系统类加载器的父类加载器，得到扩展类加载器
            ClassLoader extensionloader=systemloader.getParent();
            System.out.println("extension classloader:"+extensionloader);
            System.out.println("path of extension classLoader:"+extensionloader.getParent());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
