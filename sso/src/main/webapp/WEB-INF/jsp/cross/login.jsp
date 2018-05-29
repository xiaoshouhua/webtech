<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跨域SSO-登录页面</title>
<style type="text/css">
*{margin:0px;padding:0px;}
.contain{width:400px;height:300px;margin:300px auto;border:}
</style>
</head>
<body>
<div class="contain">
<form action="/${path}/doLogin.html" method="post">
<h1>----------登录----------</h1>
  用户名:<input type="text" name="username" /><br/>
  密&nbsp;&nbsp;码:<input type="password" name="password" /><br/>
 <input type="hidden" name="gotoUrl" value="${gotoUrl}" />
 <input type="submit" value="登录"> 
</form>
</div>
</body>
</html>