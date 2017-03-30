package reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestCompileClassLoader extends ClassLoader{
	//��ȡ�������ļ���
	private byte[] getBytes(String fileName){
		byte[] result=null;
		try{
			File file=new File(fileName);
			FileInputStream fis=new FileInputStream(file);
			int len=(int) file.length();
			result=new byte[len];
			int r=fis.read(result);
			if(r!=len){
				System.out.println("cannot read file");
			}
			fis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	//�������ָ��java�ļ��ķ���
	private boolean compile(String javaFile){
		Process p=null;
		try{
			System.out.println("compiling "+javaFile+"...");
			//����ϵͳjavac
			p=Runtime.getRuntime().exec("javac "+javaFile);
			//�����̵߳ȴ����߳�ִ����ϣ�
			p.waitFor();
			
		}catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
		int ret=p.exitValue();
		//����״ֵ̬Ϊ0��ʾִ�гɹ�
		return ret==0;
	}
	//��дclassLoader��findClass����;
	protected Class<?> findClass(String name) throws ClassNotFoundException{
		Class<?> cla=null;
		String fileStub=name.replace(".", "/");
		String javaFile=fileStub+".java";
		String classFile=fileStub+".class";
		File jFile=new File(javaFile);
		File cFile=new File(classFile);
		if(jFile.exists()&&!cFile.exists()
				||jFile.lastModified()>cFile.lastModified()){
			if(!compile(javaFile)||!cFile.exists()){
				throw new ClassNotFoundException(
					"ClassNotFoundException "+javaFile);
			}
		}
		if(cFile.exists()){
			byte[] buff=getBytes(classFile);
			cla=defineClass(name,buff,0,buff.length);
		}
		if(cla==null){
			throw new ClassNotFoundException("ClassNotFoundException "+name);
		}
		return cla;
	}
	public static void main(String[] args) throws Exception {
		String pro="Test";
		TestCompileClassLoader ccl=new TestCompileClassLoader();
		Class<?> cla=ccl.loadClass(pro);
		
		Method ma=cla.getMethod("main", new String[0].getClass());
		ma.invoke(null, null);
	}
}
class Test{
	public static void main(String[] args) {
		System.out.println("hello world");
	}
}





