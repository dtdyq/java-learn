package io;

import java.io.*;

/**
 * Created by dtdyq on 2017/3/7.
 *  如果多次序列化同一个对象时，java虚拟机只会在第一次
 *  转换成字节序列并输出
 *
 *  被transient修饰的变量不会序列化
 *
 *  用户可以自己实现writeObject()、rreadObject()
 *  从而按照自己的定义序列化指定的信息
 */
public class TestSerialize {
    public static void main(String[] args){
        try{
            ObjectOutputStream os=new ObjectOutputStream(
                    new FileOutputStream("file/io/Serialize.txt")
            );
            Person p=new Person("孙悟空",200);
            os.writeObject(p);
            ObjectInputStream oi=new ObjectInputStream(
                    new FileInputStream("file/io/Serialize.txt")
            );

            Person p1=(Person)oi.readObject();
            p.setName("猪八戒");
            os.writeObject(p);
            Person p2=(Person)oi.readObject();
            System.out.println(p1==p2);
            System.out.println("p1-->"+p1);
            System.out.println("p2-->"+p2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class Person implements Serializable{
    private String name;
    private int age;
    public Person(String name,int age){
        this.name=name;
        this.age=age;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String toString(){
        return name+"--->"+age;
    }
    private void writeObject(ObjectOutputStream os) throws IOException{
        os.writeObject(new StringBuffer(name).reverse());
        os.writeInt(age+1);
    }
    private void readObject(ObjectInputStream is)throws ClassNotFoundException,IOException{
        this.name=((StringBuffer)is.readObject()).reverse().toString();
        this.age=is.readInt()-1;
    }
}