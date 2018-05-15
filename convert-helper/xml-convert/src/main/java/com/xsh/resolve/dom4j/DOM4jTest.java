package com.xsh.resolve.dom4j;

import java.io.InputStream;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class DOM4jTest {

	public static void main(String[] args) throws DocumentException {
		
		//创建解析器
	    SAXReader reader = new SAXReader();
	    
	    InputStream input = DOM4jTest.class.getClassLoader().getResourceAsStream("resolve/user.xml");
	    
	    org.dom4j.Document document = reader.read(input);
	    org.dom4j.Element root = document.getRootElement();

	    List<org.dom4j.Element> list = root.elements();
	    for(org.dom4j.Element e:list){
	    	//获取属性值
	        String no = e.attributeValue("no");
	        String name = e.element("name").getText();
	        String age = e.element("age").getText();
	        System.out.println(no+name+age);
	    }
	}
	
}
