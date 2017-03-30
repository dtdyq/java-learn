package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 2017/3/26.
 * URL是统一资源定位器，是指向互联网资源的指针，提供的方法：
 *      getFile(): 获取URL的资源名
 *      getHost(): 获取URL的主机名
 *      getPath(): 获取URL的路径部分
 *      getPort(): 获取URL的端口号
 *      getProtocol(): 获取URL的协议名称
 *      getQuery(): 获取URL的查询字符串部分
 *      openConnection(): 返回URLConnection对象，代表与URL所引用的远程对象的连接
 *      openStream(): 打开与此URL的连接，返回InputStream
 */
public class TestURL {
    public static void main(String[] args) throws IOException {
        final DTDDown down=new DTDDown("https://github.com/brianway/" +
                "java-learning/archive/master.zip"
            ,"file/socket/javalearning.zip",8);
        down.download();
        new Thread(()->{
            while(down.getCompleteRate()<1){
                System.out.println("has download: "+down.getCompleteRate());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
//多线程下载工具类：
class DTDDown{
    //下载资源的路径：
    private String path;
    //指定所现在文件的保存路径：
    private String targetFile;
    //定义需要用多少个线程下载资源：
    private int threadNum;
    //定义下载的线程对象：
    private DownThread[] threads;
    //定义下载文件的总大小：
    private int fileSize;
    public DTDDown(String path,String targetFile,int ThreadNum){
        this.path=path;
        this.targetFile=targetFile;
        this.threadNum=ThreadNum;
        threads=new DownThread[threadNum];
    }
    public void download() throws IOException {
        URL url=new URL(path);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept","*/*");
        conn.setRequestProperty("Accept-language","zh-CN");
        conn.setRequestProperty("Charset","UTF-8");
        conn.setRequestProperty("Connection","keep-Alive");
        //获得文件大小：
        fileSize=conn.getContentLength();
        conn.disconnect();
        int currentPartSize=fileSize/threadNum+1;
        RandomAccessFile file=new RandomAccessFile(targetFile,"rw");
        //设置本地文件大小：
        file.setLength(fileSize);
        file.close();
        for(int i=0;i<threadNum;i++){
            //计算每个线程下载的开始位置：
            int startPos=i*currentPartSize;
            //每个线程使用一个RandomAccessFile进行下载：
            RandomAccessFile currentPart=new RandomAccessFile(targetFile,"rw");
            //定位该线程的下载位置：
            currentPart.seek(startPos);
            //创建下载线程：
            threads[i]=new DownThread(startPos,currentPartSize,currentPart);
            threads[i].start();
        }
    }
    //获取下载的完成百分比：
    public double getCompleteRate(){
        //统计多个线程下载的总大小：
        int sumSize=0;
        for(int i=0;i<threadNum;i++){
            sumSize+=threads[i].length;
        }
        return sumSize*1.0/fileSize;
    }
    private class DownThread extends Thread{
        //当前线程的下载位置：
        private int startPos;
        //定义当前线程负责下载的文件大小：
        private int currentPartSize;
        //当前线程需要下载的文件块：
        private RandomAccessFile currentPart;
        //定义该线程已下载的字节数：
        public int length=0;
        public DownThread(int startPos,int currentPartSize,
                          RandomAccessFile file){
            this.startPos=startPos;
            this.currentPartSize=currentPartSize;
            this.currentPart=file;
        }
        public void run(){
            try{
                URL url=new URL(path);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5*100);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept","*/*");
                conn.setRequestProperty("Accept-language","zh-CN");
                conn.setRequestProperty("Charset","UTF-8");

                InputStream inStream=conn.getInputStream();
                //跳过指定的字节：
                inStream.skip(startPos);
                byte[] buffer=new byte[1024];
                int hasRead;
                while(length<currentPartSize&&
                        (hasRead=inStream.read(buffer))!=-1){
                    currentPart.write(buffer,0,hasRead);
                    length+=hasRead;
                }
                currentPart.close();
                inStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}











