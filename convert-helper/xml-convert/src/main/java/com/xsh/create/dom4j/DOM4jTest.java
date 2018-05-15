package com.xsh.create.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class DOM4jTest {

	/**
	 * 1.引入JAR包
	 * 2.通过DocumentHelper类的createDocument()创建Document对象
	 * 3.通过Document的addElement()方法创建节点
	 * 4.通过Element的addAttribute()方法为节点添加属性
	 * 5.通过Element的setText()方法为节点设置内容
	 * 6.通过OutputFormat的createPrettyPrint()方法创建OutputFormat对象（会自动缩进、换行）
	 * 7.创建XMLWriter对象，将目的文件包装成OutputStream传入构造方法中，并将OutputFormat对象一并传入其中
	 * 8.通过XMLWriter的write()方法生成XML文件，并将Document对象作为参数传入
	 * 9.关闭XMLWriter对象
	 */
	
	public static void createXMLByDOM4J(File dest) {
        // 创建Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element rss = document.addElement("rss");
        //为rss根节点添加属性
        rss.addAttribute("version", "2.0");
        // 创建channel子节点
        Element channel = rss.addElement("channel");
        // 创建title子节点
        Element title = channel.addElement("title");
        // 设置title节点的值
        title.setText("<![CDATA[上海移动互联网产业促进中心正式揭牌 ]]>");

        // 创建输出格式(OutputFormat对象)
        OutputFormat format = OutputFormat.createPrettyPrint();

        //设置输出文件的编码
        //format.setEncoding("GBK");

        try {
            // 创建XMLWriter对象
            XMLWriter writer = new XMLWriter(new FileOutputStream(dest), format);

            //设置不自动进行转义
            writer.setEscapeText(false);

            // 生成XML文件
            writer.write(document);

            //关闭XMLWriter对象
            writer.close();
            
            System.out.println("利用dom4j生成xml文件成功,文件路径为:"+dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		File file = new File("");
		File dest = new File(file.getAbsolutePath()+"/src/main/resources/resolve/dom4j_msg.xml");
		
		createXMLByDOM4J(dest);
	}

}
