package com.create.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class CreateTest {

	/**
	 * DOM方式生成xml文档
	 */
	private void DOMCreateXML() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document document = db.newDocument();
		document.setXmlStandalone(true);
		Element bookstore = document.createElement("bookStore");
		Element book = document.createElement("book");
		Element name = document.createElement("name");
		name.setTextContent("小王子");
		book.appendChild(name);
		book.setAttribute("id", "1");
		bookstore.appendChild(book);
		document.appendChild(bookstore);
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(document), new StreamResult(new File("books1.xml")));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * SAX方式生成xml文档
	 */
	public void SAXCreateXML() {
		SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		try {
			TransformerHandler handler = tff.newTransformerHandler();
			Transformer tr = handler.getTransformer();
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			File f = new File("books2.xml");
			if (!f.exists()) {
				f.createNewFile();
			}
			Result result = new StreamResult(new FileOutputStream(f));
			handler.setResult(result);
			handler.startDocument();
			AttributesImpl attr = new AttributesImpl();
			handler.startElement("", "", "bookstore", attr);
			attr.clear();
			attr.addAttribute("", "", "id", "", "1");
			handler.startElement("", "", "book", attr);
			attr.clear();
			handler.startElement("", "", "name", attr);
			handler.characters("小王子".toCharArray(), 0, "小王子".length());
			handler.endElement("", "", "name");

			handler.endElement("", "", "book");
			handler.endElement("", "", "bookstore");
			handler.endDocument();

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	/**
	 * JDOM方式生成xml文档
	 */
	private void JDOMCreateXML() {
		org.jdom.Element bookstore = new org.jdom.Element("bookstore");
		org.jdom.Document document = new org.jdom.Document(bookstore);

		org.jdom.Element book = new org.jdom.Element("book");
		book.setAttribute("id", "1");
		org.jdom.Element name = new org.jdom.Element("name");
		name.setText("小王子");
		book.addContent(name);
		bookstore.addContent(book);
		Format format = Format.getCompactFormat();
		format.setIndent("");
		format.setEncoding("UTF-8");
		XMLOutputter outputer = new XMLOutputter(format);
		try {
			outputer.output(document, new FileOutputStream(new File("books3.xml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DOM4J方式生成xml文档
	 */
	private void DOM4JCreateXML() {
		org.dom4j.Document document = DocumentHelper.createDocument();
		org.dom4j.Element bookstore = document.addElement("bookstore");
		org.dom4j.Element book = bookstore.addElement("book");
		book.addAttribute("id", "1");
		org.dom4j.Element name = book.addElement("name");
		name.setText("小王子");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		File file = new File("books4.xml");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPerformance() throws Exception {
		System.out.println("性能测试:");
		
		// 测试DOM的性能:
		long start = System.currentTimeMillis();
		DOMCreateXML();
		System.out.println("DOM:" + (System.currentTimeMillis() - start));

		// 测试SAX的性能:
		start = System.currentTimeMillis();
		SAXCreateXML();
		System.out.println("SAX:" + (System.currentTimeMillis() - start));
		
		// 测试JDOM的性能:
		start = System.currentTimeMillis();
		JDOMCreateXML();
		System.out.println("JDOM:" + (System.currentTimeMillis() - start));
		
		// 测试DOM4J的性能:
		start = System.currentTimeMillis();
		DOM4JCreateXML();
		System.out.println("DOM4J:" + (System.currentTimeMillis() - start));
	}
}
