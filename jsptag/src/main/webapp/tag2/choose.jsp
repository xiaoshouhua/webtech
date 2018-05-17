<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/tld/tag2.tld" prefix="tx" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签-SimpleTagSupport实现choose标签</title>
</head>
<body>
<%
	pageContext.setAttribute("name", "xsh");
%>
<tx:choose>
	<tx:when test="${name == 'xsh'}">
		when内部 == 欢迎来到我的网站,您的登录时间是:<tx:loginDate/><br/>
	</tx:when>
	<tx:otherWise>
	    otherWise内部 == 欢迎来到我的网站,您的登录时间是:<tx:loginDate/><br/>
	</tx:otherWise>
</tx:choose>
</body>
</html>