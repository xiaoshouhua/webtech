package com.xsh.resolve.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAXTest {

	/**
	 * <1>先由SAXParserFactory这个工厂的实例生产一个SAXParser解析器；
	 * <2>然后根据读取的xml路径，传递给SAXParser这个解析器，再调用parse()方法；
	 * <3>在parse()方法中需要传递DefaultHandler这个类的扩展类的实例，因为它才会真正去一步步去解析xml文档的；
	 * <4>在DefaultHandler扩展类中需要重写startDocument()，endDocument()等等方法
	 */
	public static void main(String[] args) {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser saxPerser = factory.newSAXParser();
			
			saxPerser.parse("src/main/resources/resolve/baseinfo.xml", new SaxParserHandler());
			
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
