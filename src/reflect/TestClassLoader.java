package reflect;

import java.net.URL;

/**
 * Created by dtdyq on 2017/3/13.
 *  类加载器负责将.class文件加载到内存中，并为之生成相应的Class对象
 *  一个类被加载到JVM后，同一个类不会被再次加载
 *  在JVM中一个类用其全限定类名和其类加载器作为其唯一标识
 *
 *  JVM启动时，会形成由三个类加载器组成的初始化类加载器层次结构：
 *      BootStrap ClassLoader：根类加载器，负责加载java核心类，并不是ClassLoader的子类
 *          而是由JVM自身实现；
 *      Extension ClassLoader：扩展类加载器，负责加载jre的扩展目录中jar包的类
 *      System ClassLoader：系统类加载器，负责在JVM启动时加载来自java命令的
 *      -classpath选项、java.class.path系统属性
 */
public class TestClassLoader {
    public static void main(String[] args){
        //根类加载器加载的核心类：
        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url:urls){
            System.out.println(url);
        }
    }
}
