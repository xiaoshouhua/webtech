package com.xsh.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * TagSupport之控制【是否执行标签结束后的内容】
 * 简单防盗链功能
 * @author shouhua.xiao
 *
 */
public class SkipOrEvalPageTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String referer = request.getHeader("referer");
		
		String host = "http://"+request.getServerName();
		System.out.println(host);
		
		if(null != referer && referer.startsWith(host)) {
			return EVAL_PAGE;
		}else {
			return SKIP_PAGE;
		}
	}

//	@Override
//	public int doStartTag() throws JspException {
//		
//		//从请求中获取参数
//		String name = pageContext.getRequest().getParameter("name");
//		if(null != name && name.contains("xsh")) {
//			//执行标签体
//			return EVAL_BODY_INCLUDE;
//		}
//		else {
//			//忽略标签体
//			return SKIP_BODY;
//		}
//	}

	
	
}
