package com.xsh.resolve.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMTest {
	
	enum NodeType{
//		ELEMENT_NODE,ATTRIBUTE_NODE,TEXT_NODE
		ELEMENT(1,"Element"),
		Attr(2,"Attr"),
		TEXT(3,"Text");
		
		private int type;
		
		private String name;
		
		private NodeType(int type,String name) {
			this.type = type;
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public String getName() {
			return name;
		}
		
	}

	public static void main(String[] args) {
		
		//1.获得一个文档工厂对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			//2.获得一个DocumentBuilder对象
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();

			//3.加载xml到document中
//			Document document = documentBuilder.parse("src/main/resources/resolve/city_cn.xml");
			Document document = documentBuilder.parse("src/main/resources/resolve/baseinfo.xml");
			
			Element element = document.getDocumentElement();
			
			loopNode(element.getChildNodes());
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loopNode(NodeList elementList) {
		//节点列表
		if(null != elementList) {
			for (int j = 0; j < elementList.getLength(); j++) {
				Node node2 = elementList.item(j);
				if(NodeType.TEXT.type == node2.getNodeType()) {
					continue;
				}
				
				if(NodeType.Attr.type == node2.getNodeType()) {
					loopAttr(node2);
					continue;
				}
				
				if(NodeType.ELEMENT.type == node2.getNodeType()) {
					System.out.println("[2]节点名称:"+node2.getNodeName()+",节点文本:"+node2.getTextContent());
					loopNode(node2.getChildNodes());
					loopAttr(node2);
				}
			}
		}
	}

	private static void loopAttr(Node node2) {
		NamedNodeMap nodeMap = node2.getAttributes();
		if(null != nodeMap) {
			for (int k = 0; k < nodeMap.getLength(); k++) {
				Node attrNode = nodeMap.item(k);
				System.out.println("[3]属性名:"+attrNode.getNodeName()+",属性值:"+attrNode.getNodeValue());
			}
		}
	}

}
