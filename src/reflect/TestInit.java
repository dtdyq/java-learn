package reflect;

/**
 * Created by dtdyq on 2017/3/13.
 *JVM的类加载、连接和初始化：
 *  加载：由加载器完成，将对应的class文件读入内存
 *      （系统中所有类的=都是java.lang.class的实例）
 *      类来源：本地文件系统的class文件
 *             jar包中加载的class文件
 *             通过网络加载的class文件
 *             将java'文件动态编译并加载
 *  连接：把类的二进制数据合并到jre中，
 *      验证：检验被加载的类是否有正确的内部结构，并和其他类协调一致
 *      准备：负责为类的类变量分配内存，并设置默认初始值
 *      解析：将二进制数据的符号引用代替为直接引用
 *  初始化：
 *      父类没有被初始化，则先初始化所有父类
 *      类中如果有初始化语句，则执行初始化语句
 *
*/
public class TestInit {
    static{
        b=6;
        System.out.println("--------");
    }
    static int a=6;
    static int b=9;
    static int c;
    public static void main(String[] args){
        System.out.println(TestInit.b);
    }
}
