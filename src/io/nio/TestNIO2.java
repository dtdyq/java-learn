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
 *   java7��ԭ�е�NIO�����˸Ľ���
 *      �ṩ���ļ�IO���ļ�ϵͳ����֧��
 *      �����첽Channel��IO
 *   ��Щ���ṩ�ķ�����ʵ�ö��ҷḻ
 */
public class TestNIO2 {
    public static void pathTest(){
        Path path= Paths.get("file/io");
        //��ȡpath�а�����·��������
        System.out.println(path.getNameCount());
        //��ȡpath��Ӧ�ľ���·����
        Path apath=path.toAbsolutePath();
        //��ȡ����·���ĸ�·����
        System.out.println(apath.getRoot());
        //��ȡ����·����������·��������
        System.out.println(apath.getNameCount());
        System.out.println(apath.getName(2));
        //�Զ��String����������Path����
        Path p=Paths.get("D:/","name","test");
        System.out.println(p);
    }
    public static void filesTest(){
        try{
            //�����ļ���
            Files.copy(Paths.get("file/io/FileTest.txt")
                    ,new FileOutputStream("file/io/NIO.txt"));
            //һ���Զ�ȡ�ļ��е������У�
            List<String> lines=Files.readAllLines(Paths.get("file/io/Channel.txt"));
            for(String line:lines){
                System.out.println(line);
            }

            //�жϸ����ļ��Ƿ�Ϊ�����ļ���
            System.out.println(Files.isHidden(Paths.get("file/io/FileTest.txt")));
            //�ж�ָ���ļ��Ĵ�С��
            System.out.println(Files.size(Paths.get("file/io/FileTest.txt")));

            //һ��д����У�
            List<String> wr=new LinkedList<>();
            wr.add("��Ϸ��Ҷ��");
            wr.add("��Ϸ��Ҷ��");
            Files.write(Paths.get("file/io/list.txt"),wr, Charset.forName("GBK"));

            //�г�ָ��Ŀ¼�µ����������ļ���
            Files.list(Paths.get("file/io/")).forEach(path->System.out.println(path));
            //java8:
            //��ȡ�ļ����ݣ�
            Files.lines(Paths.get("file/io/list.txt"),Charset.forName("GBK")).forEach(line->System.out.println(line));

            //�ж�C�̹��ռ䣬���ÿռ䣺
            FileStore cstore=Files.getFileStore(Paths.get("C:"));
            System.out.println("all-->"+cstore.getTotalSpace());
            System.out.println("avaliable-->"+cstore.getUsableSpace());

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //ʹ��FileVisitor�����ļ���Ŀ¼��
    public static void FileVisitorTest() throws Exception{
        Files.walkFileTree(Paths.get("file/"),new FileVisitor<Path>(){
            //��ʼ����Ŀ¼ʱ�����÷�����
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("���ڷ��ʣ�"+dir+" Ŀ¼��");
                return FileVisitResult.CONTINUE;
            }
            //�����ļ�ʱ�����÷�����
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("���ڷ��ʣ� "+file+" �ļ�");
                if(file.endsWith("1.txt")){
                    System.out.println("�Ѿ��ҵ��ļ���");
                    //ֹͣ���ʣ�
                    return FileVisitResult.TERMINATE;
                }
                //�������ʣ�
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
    //ʹ��WatchService����ļ��仯��
    public static void WatchServiceTest(){
        try{
            WatchService ws=FileSystems.getDefault().newWatchService();
            Paths.get("file/io").register(ws,StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
            while(true){
                //����ʽ�����仯�¼���
                WatchKey key=ws.take();
                for(WatchEvent<?> event:key.pollEvents()){
                    System.out.println(event.context()+"--->"+event.kind());
                }
                //����WatchKey
                boolean valid=key.reset();
                //����ʧ�ܣ����˳���
                if(!valid){
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //�����ļ����ԣ�
    public static void attributeViewTest(){
        try{
            Path path=Paths.get("file/io/FileTest.txt");
            //���ʻ������ԣ�
            BasicFileAttributeView bf=Files.getFileAttributeView(path,BasicFileAttributeView.class);
            BasicFileAttributes ba=bf.readAttributes();
            System.out.println("����ʱ��-->"+ba.creationTime());
            System.out.println("������ʱ��-->"+ba.lastAccessTime());
            System.out.println("����޸�ʱ��-->"+ba.lastModifiedTime());
            System.out.println("�ļ���С-->"+ba.size());

            //�����ļ�������Ϣ��
            FileOwnerAttributeView fv=Files.getFileAttributeView(path, FileOwnerAttributeView.class);
            System.out.println("�ļ������û�-->"+fv.getOwner());

            //��ȡ�Զ������ԣ�
            UserDefinedFileAttributeView uf=Files.getFileAttributeView(path,UserDefinedFileAttributeView.class);
            //���������Զ������ԣ�
            List<String> attrname=uf.list();
            System.out.println(attrname);
            for(String name:attrname){
                ByteBuffer buf= ByteBuffer.allocate(uf.size(name));
                uf.read(name,buf);
                buf.flip();
                String str=Charset.defaultCharset().decode(buf).toString();
                System.out.println(name+"-->"+str);
            }

            //��ȡ����das���ԣ�
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
