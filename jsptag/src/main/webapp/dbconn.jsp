<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/tld/tag.tld" prefix="tx" %>

<%   
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);   
%> 

<% 
	pageContext.setAttribute("driver","com.mysql.jdbc.Driver");
	pageContext.setAttribute("url","jdbc:mysql://127.0.0.1:3306/test");
	pageContext.setAttribute("username","root");
	pageContext.setAttribute("password","root123456");
	pageContext.setAttribute("sql", "select * from tb_user ");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签之连接器</title>
</head>
<body>
<h3>hello,我的亲亲</h3>
<tx:conn  driver="${driver}" url="${url}" username="${username}" password="${password}" sql="${sql}">
</tx:conn>
</body>
</html>
