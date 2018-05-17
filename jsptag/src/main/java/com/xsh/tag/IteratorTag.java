package com.xsh.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * TagSupport之 循环标签
 * @author shouhua.xiao
 *
 */
public class IteratorTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String var;
	
	private String[] items;
	
	private int i = 1;

	@Override
	public int doStartTag() throws JspException {
		if(null != items && items.length > 0) {
			pageContext.setAttribute(var, items[0]);
			return EVAL_BODY_INCLUDE;
		}else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		if(i < items.length) {
			pageContext.setAttribute(var, items[i]);
			i++;
			//继续执行标签体
			return EVAL_BODY_AGAIN; 
		}else {
			//执行结束
			return SKIP_BODY;
		}
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	
}
