<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>同父域SSO-1111111</title>
</head>
<body>
<h1>欢迎来到测试地址:1111,hello!!</h1>
<c:forEach var="hiddenUrl" items="${hiddenUrls}">
<iframe src="${hiddenUrl}" width="0" height="0" style="display:none;"></iframe>	
</c:forEach>
</body>
</html>