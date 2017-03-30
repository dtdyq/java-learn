package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 2017/3/26.
 * URL��ͳһ��Դ��λ������ָ��������Դ��ָ�룬�ṩ�ķ�����
 *      getFile(): ��ȡURL����Դ��
 *      getHost(): ��ȡURL��������
 *      getPath(): ��ȡURL��·������
 *      getPort(): ��ȡURL�Ķ˿ں�
 *      getProtocol(): ��ȡURL��Э������
 *      getQuery(): ��ȡURL�Ĳ�ѯ�ַ�������
 *      openConnection(): ����URLConnection���󣬴�����URL�����õ�Զ�̶��������
 *      openStream(): �����URL�����ӣ�����InputStream
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
//���߳����ع����ࣺ
class DTDDown{
    //������Դ��·����
    private String path;
    //ָ���������ļ��ı���·����
    private String targetFile;
    //������Ҫ�ö��ٸ��߳�������Դ��
    private int threadNum;
    //�������ص��̶߳���
    private DownThread[] threads;
    //���������ļ����ܴ�С��
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
        //����ļ���С��
        fileSize=conn.getContentLength();
        conn.disconnect();
        int currentPartSize=fileSize/threadNum+1;
        RandomAccessFile file=new RandomAccessFile(targetFile,"rw");
        //���ñ����ļ���С��
        file.setLength(fileSize);
        file.close();
        for(int i=0;i<threadNum;i++){
            //����ÿ���߳����صĿ�ʼλ�ã�
            int startPos=i*currentPartSize;
            //ÿ���߳�ʹ��һ��RandomAccessFile�������أ�
            RandomAccessFile currentPart=new RandomAccessFile(targetFile,"rw");
            //��λ���̵߳�����λ�ã�
            currentPart.seek(startPos);
            //���������̣߳�
            threads[i]=new DownThread(startPos,currentPartSize,currentPart);
            threads[i].start();
        }
    }
    //��ȡ���ص���ɰٷֱȣ�
    public double getCompleteRate(){
        //ͳ�ƶ���߳����ص��ܴ�С��
        int sumSize=0;
        for(int i=0;i<threadNum;i++){
            sumSize+=threads[i].length;
        }
        return sumSize*1.0/fileSize;
    }
    private class DownThread extends Thread{
        //��ǰ�̵߳�����λ�ã�
        private int startPos;
        //���嵱ǰ�̸߳������ص��ļ���С��
        private int currentPartSize;
        //��ǰ�߳���Ҫ���ص��ļ��飺
        private RandomAccessFile currentPart;
        //������߳������ص��ֽ�����
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
                //����ָ�����ֽڣ�
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











