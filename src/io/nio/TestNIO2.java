package io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.LinkedList;
import java.util.List;
import java.nio.file.attribute.*;

/**
 * Created by dtdyq on 2017/3/8.
 *   java7对原有的NIO进行了改进：
 *      提供了文件IO和文件系统访问支持
 *      基于异步Channel的IO
 *   这些新提供的方法，实用而且丰富
 */
public class TestNIO2 {
    public static void pathTest(){
        Path path= Paths.get("file/io");
        //获取path中包含的路径数量：
        System.out.println(path.getNameCount());
        //获取path对应的绝对路径：
        Path apath=path.toAbsolutePath();
        //获取绝对路径的根路径：
        System.out.println(apath.getRoot());
        //获取绝对路径所包含的路径数量：
        System.out.println(apath.getNameCount());
        System.out.println(apath.getName(2));
        //以多个String类型来构建Path对象：
        Path p=Paths.get("D:/","name","test");
        System.out.println(p);
    }
    public static void filesTest(){
        try{
            //复制文件：
            Files.copy(Paths.get("file/io/FileTest.txt")
                    ,new FileOutputStream("file/io/NIO.txt"));
            //一次性读取文件中的所有行：
            List<String> lines=Files.readAllLines(Paths.get("file/io/Channel.txt"));
            for(String line:lines){
                System.out.println(line);
            }

            //判断给定文件是否为隐藏文件：
            System.out.println(Files.isHidden(Paths.get("file/io/FileTest.txt")));
            //判断指定文件的大小：
            System.out.println(Files.size(Paths.get("file/io/FileTest.txt")));

            //一次写入多行：
            List<String> wr=new LinkedList<>();
            wr.add("鱼戏莲叶南");
            wr.add("鱼戏莲叶北");
            Files.write(Paths.get("file/io/list.txt"),wr, Charset.forName("GBK"));

            //列出指定目录下的所有所有文件：
            Files.list(Paths.get("file/io/")).forEach(path->System.out.println(path));
            //java8:
            //读取文件内容：
            Files.lines(Paths.get("file/io/list.txt"),Charset.forName("GBK")).forEach(line->System.out.println(line));

            //判断C盘共空间，可用空间：
            FileStore cstore=Files.getFileStore(Paths.get("C:"));
            System.out.println("all-->"+cstore.getTotalSpace());
            System.out.println("avaliable-->"+cstore.getUsableSpace());

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //使用FileVisitor遍历文件和目录：
    public static void FileVisitorTest() throws Exception{
        Files.walkFileTree(Paths.get("file/"),new FileVisitor<Path>(){
            //开始访问目录时触发该方法：
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问："+dir+" 目录。");
                return FileVisitResult.CONTINUE;
            }
            //访问文件时触发该方法：
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问： "+file+" 文件");
                if(file.endsWith("1.txt")){
                    System.out.println("已经找到文件。");
                    //停止访问：
                    return FileVisitResult.TERMINATE;
                }
                //继续访问：
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return null;
            }
        });
    }
    //使用WatchService监控文件变化：
    public static void WatchServiceTest(){
        try{
            WatchService ws=FileSystems.getDefault().newWatchService();
            Paths.get("file/io").register(ws,StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
            while(true){
                //阻塞式监听变化事件：
                WatchKey key=ws.take();
                for(WatchEvent<?> event:key.pollEvents()){
                    System.out.println(event.context()+"--->"+event.kind());
                }
                //重设WatchKey
                boolean valid=key.reset();
                //重设失败，则退出：
                if(!valid){
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //访问文件属性：
    public static void attributeViewTest(){
        try{
            Path path=Paths.get("file/io/FileTest.txt");
            //访问基本属性：
            BasicFileAttributeView bf=Files.getFileAttributeView(path,BasicFileAttributeView.class);
            BasicFileAttributes ba=bf.readAttributes();
            System.out.println("创建时间-->"+ba.creationTime());
            System.out.println("最后访问时间-->"+ba.lastAccessTime());
            System.out.println("最后修改时间-->"+ba.lastModifiedTime());
            System.out.println("文件大小-->"+ba.size());

            //访问文件属主信息：
            FileOwnerAttributeView fv=Files.getFileAttributeView(path, FileOwnerAttributeView.class);
            System.out.println("文件所属用户-->"+fv.getOwner());

            //获取自定义属性：
            UserDefinedFileAttributeView uf=Files.getFileAttributeView(path,UserDefinedFileAttributeView.class);
            //遍历所有自定义属性：
            List<String> attrname=uf.list();
            System.out.println(attrname);
            for(String name:attrname){
                ByteBuffer buf= ByteBuffer.allocate(uf.size(name));
                uf.read(name,buf);
                buf.flip();
                String str=Charset.defaultCharset().decode(buf).toString();
                System.out.println(name+"-->"+str);
            }

            //获取访问das属性：
            DosFileAttributeView df=Files.getFileAttributeView(path,DosFileAttributeView.class);
            df.setHidden(false);
            df.setReadOnly(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception{
        pathTest();
        filesTest();
        FileVisitorTest();
        WatchServiceTest();
        attributeViewTest();
    }
}
