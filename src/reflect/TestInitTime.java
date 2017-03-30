package reflect;

/**
 * Created by dtdyq on 2017/3/13.
 *
 * 类初始化的时间：java程序首次通过下面六种方式使用某个类或接口时，会执行初始化：
 *      创建类的实例：new关键字；通过反射来创建实例；通过反序列化类创建实例
 *      调用某个类的类方法（静态方法）
 *      访问某个接口或类的类变量
 *      使用反射方式强制创建某个类或接口对应的java.lang.class对象
 *      直接通过java.exe来运行某个主类
 *      初始化某个类的子类
 *
 * final修饰的类变量，如果该类变量的值在编译时就可以确定下来，则
 * 这个类变量相当于“宏变量”，java编译器会在编译时直接把该类变量出
 * 现的地方替换成它的值，不会导致该类的初始化：
 * 但是类变量在编译时不能确定下来，则需要对类进行初始化：
 */
public class TestInitTime {
    public static void main(String[] args) throws ClassNotFoundException{
        //不会导致CompileConstant的初始化：
        System.out.println(CompileConstant.compileConstant);
        //会导致CompileConstant的初始化：
        System.out.println(CompileConstant.compile);

        ClassLoader cl=ClassLoader.getSystemClassLoader();;
        cl.loadClass("Loader");

        Class.forName("Loader");
    }
}
class CompileConstant{
    static {
        System.out.println("static field...");
    }
    static final String compileConstant="staticfinaltype";
    static final String compile=System.currentTimeMillis()+"---";
}
/**
 * 当使用ClassLoader类的loadClass()方法时，该方法只是加载类，不会执行初始化，
 * 使用Class.forName()时会导致强制初始化该类
 */
class Loader{
    static{
        System.out.println("initilize...");
    }
}