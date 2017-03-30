package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by dtdyq on 2017/3/29.
 * 使用java的NIO实现非阻塞Socket通信
 */
class NServer{
    //用于检测所有Channel状态的Selector
    private Selector selector=null;
    static final int PORT=30000;
    //定义实现编码解码的字符集对象：
    private Charset charset=Charset.forName("utf-8");
    public void init()throws IOException {
        selector=Selector.open();
        //通过open方法打开一个未绑定的ServerSocketChannel实例：
        ServerSocketChannel server=ServerSocketChannel.open();
        InetSocketAddress isa=new InetSocketAddress("127.0.0.1",PORT);
        //将server绑定打指定IP地址：
        server.bind(isa);
        //设置server以非阻塞方式工作：
        server.configureBlocking(false);
        //将server注册到指定的Selector对象：
        server.register(selector,16);
        while(selector.select()>0){
            //依次处理Selector上每个SelectionKey
            for(SelectionKey sk:selector.selectedKeys()){
                //从Selector已选择的Key集合上删除正在处理的SelectionKey
                selector.selectedKeys().remove(sk);
                //处理客户端的连接请求
                if(sk.isAcceptable()){
                    SocketChannel scc=server.accept();
                    //设置采用非阻塞模式并注册selector：
                    scc.configureBlocking(false);
                    scc.register(selector,SelectionKey.OP_READ);
                    //将sk对应的channel设置成准备接受其他请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }

                //接受客户端的数据
                if(sk.isReadable()){
                    SocketChannel sc=(SocketChannel)sk.channel();
                    ByteBuffer buff=ByteBuffer.allocate(1024);
                    String content="";
                    try{
                        while(sc.read(buff)>0){
                            buff.flip();
                            content+=charset.decode(buff);
                        }
                        System.out.println(content);
                        //将sk对应的channel设置成对应下一次读取
                        sk.interestOps(SelectionKey.OP_READ);
                    //处理异常
                    }catch(Exception e){
                        sk.channel();
                        if(sk.channel()!=null){
                            sk.channel().close();
                        }
                    }
                    if(content.length()>0){
                        for(SelectionKey key:selector.keys()){
                            Channel target=key.channel();
                            if(target instanceof SocketChannel) {
                                SocketChannel dest = (SocketChannel) target;
                                dest.write(charset.encode(content));
                            }
                        }
                    }

                }


            }
        }
    }
    public static void main(String[] args) throws IOException {
        new NServer().init();
    }
}

class NClient{
    //定义检测SocketChannel的Selector对象：
    private Selector selector=null;
    static final int PORT=30000;
    //定义编码和解码集：
    Charset charset=Charset.forName("utf-8");
    //客户端的SocketChannel
    private SocketChannel sc=null;
    public void init()throws IOException{
        selector=Selector.open();
        InetSocketAddress isa=new InetSocketAddress("127.0.0.1",PORT);
        sc=SocketChannel.open(isa);
        //设置以非阻塞方式工作;
        sc.configureBlocking(false);
        //注册到Selector对象上：
        sc.register(selector,SelectionKey.OP_READ);
        //启动读取服务端数据的线程：
        new ClientThread(selector).start();
        //获取键盘输入：
        Scanner scan=new Scanner(System.in);
        while(scan.hasNextLine()){
            String line=scan.nextLine();
            sc.write(charset.encode(line));
        }
    }

    public static void main(String[] args) throws IOException {
        new NClient().init();
    }
}
class ClientThread extends Thread{
    Selector selector=null;
    Charset charset=Charset.forName("utf-8");
    public ClientThread(Selector selector){
        this.selector=selector;
    }
    public void run(){
        try{
            while(selector.select()>0){
                for(SelectionKey sk:selector.selectedKeys()){
                    selector.selectedKeys().remove(sk);
                    if(sk.isReadable()){
                        SocketChannel scc= (SocketChannel) sk.channel();
                        ByteBuffer buff=ByteBuffer.allocate(1024);
                        String content="";
                        while(scc.read(buff)>0){
                            buff.flip();
                            content+=charset.decode(buff);
                        }
                        System.out.println(content);
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}













