package com.xsh.tag.bodytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ModifyBodyTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BodyContent bodyContent;

	@Override
	public int doEndTag() throws JspException {
		
		//标签体内容
		String content = bodyContent.getString();
		System.out.println(content);
		
		String newContent = "hello,大家好!"+content;
		
		JspWriter out = bodyContent.getEnclosingWriter();
		try {
			out.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public void setBodyContent(BodyContent b) {
		this.bodyContent = b;
	}
	
}
