package com.xsh.create.sax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class SAXTest {

	/**
	 * <1>创建保存xml的结果流对象StreamResult；
	 * <2>然后利用SAXTransformerFactory这个工厂创建TransformerHandler这个操作者；
	 * <3>操作这个TransformerHandler获取Transformer，利用Transformer创建节点信息；
	*/
	public static void main(String[] args) {

		File file = new File("");
		File outFile = new File(file.getAbsolutePath()+"/src/main/resources/resolve/sax_user.xml");
		
		createXMLByDOM(outFile);
		
	}

	private static void createXMLByDOM(File file) throws TransformerFactoryConfigurationError {
		try {  
			
	        //创建保存xml的结果流对象  
	        Result reultXml = new StreamResult(new FileOutputStream(file));  
	        //获取sax生产工厂对象实例  
	        SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();  
	        //获取sax生产处理者对象实例  
	        TransformerHandler transformerHandle = saxTransformerFactory.newTransformerHandler();  
	        transformerHandle.setResult(reultXml);  
	        //获取sax生产器  
	        Transformer transformer = transformerHandle.getTransformer();  
	        //transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");//xml的编码格式  
	        transformer.setOutputProperty(OutputKeys.INDENT,"yes");//换行
	        //开始封装document文档对象，这里和解析一样都是成双成对的构造标签  
	        transformerHandle.startDocument();  
	        AttributesImpl attrImple = new AttributesImpl();  
	        transformerHandle.startElement("", "", "Users",attrImple);  
	          
	        attrImple.addAttribute("", "", "id", "string", "123");  
	        transformerHandle.startElement("", "", "user", attrImple);  
	        transformerHandle.characters("这个是用户的信息".toCharArray(), 0, "这个是用户的信息".length());  
	        transformerHandle.endElement("", "", "user");  
	        transformerHandle.endElement("", "", "Users");  
	        //因为没有appendChild等等添加子元素的方法，sax提供的是构造在startElement()和endElement()区间内的标签为包含的节点的父节点  
	        transformerHandle.endDocument();  
	          
	        System.out.println("利用sax生成xml文档成功!生成路径为:"+file.getAbsolutePath());  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (TransformerConfigurationException e) {  
	        e.printStackTrace();  
	    } catch (SAXException e) {  
	        e.printStackTrace();  
	    }
	}

}
