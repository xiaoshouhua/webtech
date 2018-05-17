package com.xsh.function;

/**
 * 自定义函数标签:防xss跨域脚本
 * @author shouhua.xiao
 *
 */
public class TxFunctions {

	public static String htmlEncode(String txt) {
		if (txt != null) {
			txt = txt.replace("&", "&amp;").replace("&amp;amp;", "&amp;").replace("&amp;quot;", "&quot;")
					.replace("\"", "&quot;").replace("&amp;lt;", "&lt;").replace("<", "&lt;")
					.replace("&amp;gt;", "&gt;").replace(">", "&gt;").replace("&amp;nbsp;", "&nbsp;");
		}
		return txt;
	}

	public static void main(String[] args) {
		System.out.println(htmlEncode("<script >alert(</script>"));
	}
}
