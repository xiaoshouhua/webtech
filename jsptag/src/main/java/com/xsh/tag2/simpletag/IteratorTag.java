package com.xsh.tag2.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * SimpleTagSupport之 循环标签
 * @author shouhua.xiao
 *
 */
public class IteratorTag extends SimpleTagSupport {

	private String var;
	
	private String[] items;
	
	@Override
	public void doTag() throws JspException, IOException {
		if(null != items && items.length > 0) {
			PageContext pageContext = (PageContext)getJspContext();
			for (int i = 0; i < items.length; i++) {
				pageContext.setAttribute(var, items[i]);
				getJspBody().invoke(null);
			}
		}
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
}
