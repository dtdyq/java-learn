package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by dtdyq on 2017/3/29.
 * ʹ��java��NIOʵ�ַ�����Socketͨ��
 */
class NServer{
    //���ڼ������Channel״̬��Selector
    private Selector selector=null;
    static final int PORT=30000;
    //����ʵ�ֱ��������ַ�������
    private Charset charset=Charset.forName("utf-8");
    public void init()throws IOException {
        selector=Selector.open();
        //ͨ��open������һ��δ�󶨵�ServerSocketChannelʵ����
        ServerSocketChannel server=ServerSocketChannel.open();
        InetSocketAddress isa=new InetSocketAddress("127.0.0.1",PORT);
        //��server�󶨴�ָ��IP��ַ��
        server.bind(isa);
        //����server�Է�������ʽ������
        server.configureBlocking(false);
        //��serverע�ᵽָ����Selector����
        server.register(selector,16);
        while(selector.select()>0){
            //���δ���Selector��ÿ��SelectionKey
            for(SelectionKey sk:selector.selectedKeys()){
                //��Selector��ѡ���Key������ɾ�����ڴ����SelectionKey
                selector.selectedKeys().remove(sk);
                //����ͻ��˵���������
                if(sk.isAcceptable()){
                    SocketChannel scc=server.accept();
                    //���ò��÷�����ģʽ��ע��selector��
                    scc.configureBlocking(false);
                    scc.register(selector,SelectionKey.OP_READ);
                    //��sk��Ӧ��channel���ó�׼��������������
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }

                //���ܿͻ��˵�����
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
                        //��sk��Ӧ��channel���óɶ�Ӧ��һ�ζ�ȡ
                        sk.interestOps(SelectionKey.OP_READ);
                    //�����쳣
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
    //������SocketChannel��Selector����
    private Selector selector=null;
    static final int PORT=30000;
    //�������ͽ��뼯��
    Charset charset=Charset.forName("utf-8");
    //�ͻ��˵�SocketChannel
    private SocketChannel sc=null;
    public void init()throws IOException{
        selector=Selector.open();
        InetSocketAddress isa=new InetSocketAddress("127.0.0.1",PORT);
        sc=SocketChannel.open(isa);
        //�����Է�������ʽ����;
        sc.configureBlocking(false);
        //ע�ᵽSelector�����ϣ�
        sc.register(selector,SelectionKey.OP_READ);
        //������ȡ��������ݵ��̣߳�
        new ClientThread(selector).start();
        //��ȡ�������룺
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













