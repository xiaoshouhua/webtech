<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/tld/f.tld" prefix="fx" %>
<%@ taglib uri="../WEB-INF/tld/tag.tld" prefix="tx" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签-1</title>
</head>
<body>
欢迎来到我的网站,您的登录时间是:<tx:loginDate/><br/>
${fx:filter('<script >alert(</script>')}
</body>
</html>