package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by dtdyq on 2017/3/15.
 * XMLπ§æﬂ¿‡£∫
 */
public class XMLUtil {
    public static Document getDocument(String filename)throws Exception{
        return DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(filename);
    }
    public static void getDocument(File file)throws Exception{
        getDocument(file.getPath());
    }
    public static void writeDocument(Document document,String name)throws Exception{
        TransformerFactory trans=TransformerFactory.newInstance();
        Transformer tf=trans.newTransformer();
        tf.transform(new DOMSource(document),new StreamResult(name));
    }
    public static void appendNode(Document document,Node parent,String nodeName,
                                  String atr, String value,String text,String nValue){
        Element ele=document.createElement(nodeName);
        if(atr!=null) {
            ele.setAttribute(atr, value);
        }
        if(nValue!=null) {
            ele.setNodeValue(nValue);
        }
        if(text!=null){
            ele.setTextContent(text);
        }
        parent.appendChild(ele);
    }
    public static void updateNode(Element ele,String atr,String atrValue,
                                  String nValue,String text){
        if(ele.hasAttribute(atr)){
            ele.setAttribute(atr,atrValue);
        }
        ele.setNodeValue(nValue);
        ele.setTextContent(text);
    }
    public static void deleteNode(Document document,String nodeName,int index){
        Node node=document.getElementsByTagName(nodeName).item(index);
        node.getParentNode().removeChild(node);
    }
    public static void main(String[] args)throws Exception{
        Document document=XMLUtil.getDocument("file/xml/Test.xml");
        XMLUtil.appendNode(document,document.getElementsByTagName("book").item(0),
                "testnode",null,null,"98235243",null);
        XMLUtil.deleteNode(document,"book",2);
        XMLUtil.writeDocument(document,"file/xml/utilTest.xml");
    }
}
