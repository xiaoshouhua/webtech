package com.xsh.tag2.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * SimpleTagSupport之控制是否执行标签体内容
 * @author shouhua.xiao
 */
public class SkipOrEvalBodyTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		
		PageContext pageContext = (PageContext)getJspContext();
		 
		//从请求中获取参数
		String name = pageContext.getRequest().getParameter("name");
		if(null != name && name.contains("xsh")) {
			//执行标签体,显示标签体内容
			getJspBody().invoke(null);
		}
		super.doTag();
	}

	
}
