package com.xsh.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * TagSupport之控制是否执行标签体内容
 * @author shouhua.xiao
 */
public class SkipOrEvalBodyTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		
		//从请求中获取参数
		String name = pageContext.getRequest().getParameter("name");
		if(null != name && name.contains("xsh")) {
			//执行标签体
			return EVAL_BODY_INCLUDE;
		}
		else {
			//忽略标签体
			return SKIP_BODY;
		}
	}

	
}
