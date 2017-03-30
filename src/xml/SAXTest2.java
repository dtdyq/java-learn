package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * xml基于SAX的一种实现方式：
 * @author dtdyq
 *
 */
public class SAXTest2 {
	public static void main(String[] args)throws Exception{
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		//2.得到XMLReader对象
		XMLReader reader = parser.getXMLReader();
		final List<Book> books = new ArrayList<Book>();
		//3.设置内容处理器
		reader.setContentHandler(new DefaultHandler(){
			Book book=null;
			String node=null;

			@Override
			public void startElement(String uri, String localName, String qName,
					Attributes attributes) throws SAXException {
				if("book".equals(qName)){
					book=new Book();
				}
				node=qName;
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if("book".equals(qName)){
					books.add(book);
					book=null;
				}
				node=null;
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				if("name".equals(node)){
					book.setName(new String(ch,start,length));
				}
				if("author".equals(node)){
					book.setAuthor(new String(ch,start,length));
				}
				if("price".equals(node)){
					book.setPrice(Double.parseDouble(new String(ch,start,length)));
				}
			}
			
		});
		reader.parse("file/xml/Test.xml");
		for(Book book:books){
			System.out.println(book);
		}
		System.out.println("end");
	}
}
class Book{
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	private String name;
	private String author;
	private double price;
	public Book() {
		super();
	}
	public Book(String name, String author, double price) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", price=" + price + "]";
	}
	
}
