<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/tld/f.tld" prefix="fx" %>
<%@ taglib uri="../WEB-INF/tld/tag2.tld" prefix="tx" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签-SimpleTagSupport</title>
</head>
<body>
<%
	pageContext.setAttribute("name", "xsh");
%>

<tx:if test="${name == 'xsh'}">
	if内部 == 欢迎来到我的网站,您的登录时间是:<tx:loginDate/><br/>
</tx:if>
<tx:if test="${name != 'xsh'}">
	不满足if条件	
</tx:if>
</body>
</html>