package com.xsh.resolve.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserHandler extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("=== SAX 解析开始 ===");
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("=== SAX 解析结束 ===");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
//		System.out.println("uri:"+uri);
//		System.out.println("localName:"+localName);
//		System.out.println("attributes:"+attributes);
		System.out.println("节点名:"+qName);
		if(null != attributes) {
			int length = attributes.getLength();
			for (int i = 0; i < length; i++) {
				String propName = attributes.getQName(i);
				String value = attributes.getValue(i);
				System.out.println(propName+":"+value);
			}
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		System.out.println("节点"+qName+"遍历完成");
	}

	
}
