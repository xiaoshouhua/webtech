package com.xsh.create.jdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JDOMTest {
	
	private static void createXML(File dest) {  
		
        //1.生成一个根节点  
        Element rss = new Element("rss");  
        //2.为节点添加属性  
        rss.setAttribute("version", "2.0");  
        //3.生成一个document对象  
        Document document = new Document(rss);  
          
        Element channel = new Element("channel");  
        rss.addContent(channel);  
        Element title = new Element("title");  
        title.setText("<![CDATA[这是一个JDOM方式生成xml,需要导入jdom-2.0.5.jar包]]>");  
        channel.addContent(title);  
          
        Format format = Format.getCompactFormat();  
        format.setIndent("");  
        format.setEncoding("GBK");  
          
        //4.创建XMLOutputter的对象  
        XMLOutputter outputer = new XMLOutputter(format);  
        try {  
            //5.利用outputer将document对象转换成xml文档  
            outputer.output(document, new FileOutputStream(dest));
            
            System.out.println("利用jdom生成xml文件成功,文件路径为:"+dest.getAbsolutePath());
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

	public static void main(String[] args) {

		File file = new File("");
		File dest = new File(file.getAbsolutePath()+"/src/main/resources/resolve/jdom_msg.xml");
		
		createXML(dest);

	}

}
