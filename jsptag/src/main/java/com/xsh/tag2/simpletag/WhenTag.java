package com.xsh.tag2.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WhenTag extends SimpleTagSupport {
	
	private boolean test;

	@Override
	public void doTag() throws JspException, IOException {
		if(true == test) {
			//执行标签体,显示标签体内容
			getJspBody().invoke(null);
			ChooseTag chooseTag = (ChooseTag)getParent();
			chooseTag.setFlag(true);
		}
	}

	public void setTest(boolean test) {
		this.test = test;
	}

}
