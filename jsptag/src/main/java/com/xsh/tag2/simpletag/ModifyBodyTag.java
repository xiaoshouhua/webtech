package com.xsh.tag2.simpletag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * SimpleTagSupport之修改标签体内容
 * @author shouhua.xiao
 *
 */
public class ModifyBodyTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		
		StringWriter stringWriter = new StringWriter();
		JspFragment jspFragment = getJspBody();
		jspFragment.invoke(stringWriter);
		
		String content = stringWriter.toString();
		System.out.println(content);
		
		String newContent = "hello,大家好!"+content;
		
		PageContext pageContext = (PageContext)getJspContext();
		try {
			pageContext.getOut().write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
