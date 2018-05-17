<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/tld/tag2.tld" prefix="tx" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签-使用SimpleTagSupport迭代</title>
<%
	String[] datas = {"斑马斑马","安和桥","董小姐"};
	pageContext.setAttribute("datas", datas);
%>
</head>
<body>
<h1>宋冬野-歌曲列表</h1>
<tx:iterator items="${datas}" var="data">
${data}<br/>
</tx:iterator>
<h3>hello,我的亲亲mm</h3>
</body>
</html>