package com.xsh.tag2.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OtherWiseTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		ChooseTag chooseTag = (ChooseTag)getParent();
		if(!chooseTag.isFlag()) {
			getJspBody().invoke(null);
		}
	}
	
}
