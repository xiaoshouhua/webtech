package com.xsh.tag2.simpletag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * SimpleTagSupport之控制【是否执行标签结束后的内容】
 * 简单防盗链功能
 * @author shouhua.xiao
 *
 */
public class SkipOrEvalPageTag extends SimpleTagSupport {


	@Override
	public void doTag() throws JspException {
		
		PageContext pageContext = (PageContext)getJspContext();
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String referer = request.getHeader("referer");
		if(null == referer) {
			throw new SkipPageException("必须是本域中的请求才可以访问!");
		}
	}
	
}
