package com.xsh.create.dom;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xsh.resolve.dom4j.DOM4jTest;

/**
 * 
 * @author Administrator
 *
 */
public class DOMTest {

	/**
	 * 使用DOM方式生成XML文件有如下几步：
	 * 首先是创建DOM树（即规定XML文件中的内容）：
	   * 创建DocumentBuilderFactory对象
	   * 通过DocumentBuilderFactory对象创建DocumentBuilder对象
	   * 通过DocumentBuilder对象的newDocument()方法创建一个Document对象，该对象代表一个XML文件
	   * 通过Document对象的createElement()方法创建根节点
	   * 通过Document对象的createElement()方法创建N个子节点，并为他们赋值，再将这些子节点添加到根节点下
	   * 将根节点添加到Document对象下
	 * 其次是将DOM树转换为XML文件：
	   * 创建TransformerFactory类的对象
	   * 通过TransformerFactory创建Transformer对象
	   * 使用Transformer对象的transform()方法将DOM树转换为XML文件。（该方法有两个参数，第一个参数为源数据，需要创建DOMSource对象并将Document加载到其中；第二个参数为目的文件，即要生成的XML文件，需要创建StreamResult对象并指定目的文件）
	 * @throws ParserConfigurationException 
	 * 	
	 */
	public static void main(String[] args) throws Exception {
		
		File file = new File("");
		File outFile = new File(file.getAbsolutePath()+"/src/main/resources/resolve/dom_book.xml");
		
		createXMLByDOM(outFile);
	}

	private static void createXMLByDOM(File dest) throws ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerConfigurationException, TransformerException {
		//1.创建DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		//2.创建DocumentBuilder
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//3.创建Document
		Document document = builder.newDocument();
		
		//4.创建根节点
		Element bookstore = document.createElement("bookstore");
		
		//5.创建子节点，并设置属性
		Element book = document.createElement("book");
		book.setAttribute("id", "1");
		
		//6.为book添加子节点
		Element name = document.createElement("name");
		name.setTextContent("安徒生童话");
		book.appendChild(name);
		
		Element author = document.createElement("author");
		author.setTextContent("安徒生");
		book.appendChild(author);
		
		Element price = document.createElement("price");
		price.setTextContent("49");
		book.appendChild(price);
		
		//7.为根节点添加子节点
		bookstore.appendChild(book);
		
		//8.将根节点添加到Document下
		document.appendChild(bookstore);
		
		//下面开始生成XML文件
		// 创建TransformerFactory对象
		TransformerFactory tff = TransformerFactory.newInstance();
		
		// 创建Transformer对象
		Transformer tf = tff.newTransformer();
		
		// 设置输出数据时换行
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		// 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
		document.setXmlStandalone(true);
		
		// 使用Transformer的transform()方法将DOM树转换成XML
		tf.transform(new DOMSource(document), new StreamResult(dest));
		
		System.out.println("利用dom生成xml文件成功,文件路径为:"+dest.getAbsolutePath());
	}

}
