package com.xsh.tag2.simpletag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 登录时间处理标签类
 * @author shouhua.xiao
 */
public class LoginDateTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		
		PageContext pageContext = (PageContext)getJspContext();
		try {
			pageContext.getOut().print(dateStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
