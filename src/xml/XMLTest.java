package xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by dtdyq on 2017/3/14.
 * xml简单示例：
 */
public class XMLTest {
    public static void main(String[] args) throws Exception{
        //创建DocumentBuilder：
        DocumentBuilder builder=DocumentBuilderFactory.newInstance()
                 .newDocumentBuilder();
        //通过parse方法获取document对象：
        org.w3c.dom.Document document=builder.parse("file/xml/Test.xml");
        //dom获取节点方法：
        NodeList list=document.getElementsByTagName("name");
        //获取指定节点：
        Node node=list.item(0);
        System.out.println(node.getTextContent());

        //添加节点：
        Element ele=document.createElement("time");
        ele.setTextContent("2018-11-11");
        document.getElementsByTagName("book").item(0).appendChild(ele);
        TransformerFactory trans=TransformerFactory.newInstance();
        Transformer tf=trans.newTransformer();
        tf.transform(new DOMSource(document),new StreamResult("file/xml/Test.xml"));

        //创建book节点：
        Element bookEle=document.createElement("book");
        Element nameEle=document.createElement("name");
        nameEle.setTextContent("computer");
        Element authorEle=document.createElement("author");
        authorEle.setTextContent("saiu");
        Element priceEle=document.createElement("price");
        priceEle.setTextContent("543");
        bookEle.appendChild(nameEle);
        bookEle.appendChild(authorEle);
        bookEle.appendChild(priceEle);
        document.getElementsByTagName("books").item(0).appendChild(bookEle);

        tf.transform(new DOMSource(document),new StreamResult("file/xml/Test.xml"));


    }
}
