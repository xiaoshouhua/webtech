package com.xsh.tag2.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IFTag extends SimpleTagSupport {
	
	private boolean test;

	@Override
	public void doTag() throws JspException, IOException {
		
		if(true == test) {
			//执行标签体,显示标签体内容
			getJspBody().invoke(null);
		}
		
		super.doTag();
	}

	public void setTest(boolean test) {
		this.test = test;
	}

}
