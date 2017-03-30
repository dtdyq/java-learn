package xml;

import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {
	public static void main(String[] args)throws Exception{
		Document document=Dom4jTest.getDocument("file/xml/Test.xml");
		//�ҳ���һ��������ߣ�
		Element ele=document.getRootElement();
		Element book=(Element)ele.elements("book").get(0);
		Element author=book.element("author");
		System.out.println(author.getText());
		
		//Dom4jTest.printDocument(document);
		Dom4jTest.treeWalk(ele);
		
		//����һ��������߸�ΪXXXXX��
		book.element("name").setText("XXXXX");
		
		//�����ӽڵ㣺
		Element bokele=document.getRootElement();
		Element app=(Element)bokele.elements("book").get(1);
		app.addElement("time").setText("2018-12-12");
		Dom4jTest.writeDocument(document, "file/xml/Test.xml");
	}
	/**
	 * ���ڽ�ָ����xml�ļ�ת��ΪDoucment����
	 * @param url
	 * @return Document
	 * @throws Exception
	 */
	public static Document getDocument(String url) throws Exception{
		SAXReader reader=new SAXReader();
		return reader.read(url);
	}
	/**
	 * ��һ��documentд��ָ���ļ���
	 * @param document
	 * @param url
	 * @throws Exception
	 */
	public static void writeDocument(Document document,String url)throws Exception{
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		
		XMLWriter writer=new XMLWriter(new FileWriter(url),format);
		writer.write(document);
		writer.close();
	}
	/**
	 * ��ָ��document��ӡ����
	 * @param document
	 * @throws Exception
	 */
	public static void printDocument(Document document) throws Exception{
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(System.out,format);
		writer.write(document);
		
	}
	/**
	 * �ݹ��ӡ������Ԫ��
	 * @param ele
	 */
	public static void treeWalk(Element ele){
		System.out.println(ele.getName());
		for(int i=0;i<ele.nodeCount();i++){
			Node node=ele.node(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				treeWalk((Element)node);
			}
		}
	}
}








