package xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * xml����SAX��һ�ֽ�����ʽ��
 * @author dtdyq
 *
 */
public class SAXTest {
	public static void main(String[] args)throws Exception{
		SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
		parser.parse("file/xml/Test.xml", new DefaultHandler(){

			@Override
			public void startDocument() throws SAXException {
				System.out.println("end document");
			}

			@Override
			public void endDocument() throws SAXException {
				System.out.println("end document");
			}

			@Override
			public void startElement(String uri, String localName, String qName,
					Attributes attributes) throws SAXException {
				System.out.println("start element");
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				System.out.println("end document");
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				System.out.println(new String(ch,start,length));
			}
			
		});
	}

}
