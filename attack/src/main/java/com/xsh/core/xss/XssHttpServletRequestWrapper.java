package com.xsh.core.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 创建 XssHttpServletRequestWrapper 类
 * @author shouhua.xiao
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public String[] getParameterValues(String parameter) {

		String[] values = super.getParameterValues(parameter);

		if (values == null) {
			return null;
		}

		int count = values.length;
		
		String[] encodedValues = new String[count];

		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}

		return encodedValues;

	}

	public String getParameter(String parameter) {

		String value = super.getParameter(parameter);

		if (value == null) {
			return null;
		}

		return cleanXSS(value);

	}

	public String getHeader(String name) {

		String value = super.getHeader(name);

		if (value == null)
			return null;

		return cleanXSS(value);

	}

	private String cleanXSS(String value) {

		//转义 <>符号的

		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

		//转义 ()符号的
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");

		//转义 '的
		value = value.replaceAll("'", "&#39;");

		//转义双引号
		value = value.replaceAll("\"", "\"");

		//转义/的
		value = value.replaceAll("/", "&#x2f;");

		//转义双引号
		value = value.replaceAll("\"", "\"");

		//转义 函数的
		value = value.replaceAll("eval\\((.*)\\)", "");

		//转义 javascript的
		//value = value.replaceAll("[\"\'][\s]*javascript:(.*)[\"\']", """");
		value = value.replaceAll("script", "");

		return value;

	}

}
