<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的验证码</title>
<style type="text/css">
*{margin:0px;padding:0px;}
a{text-decoration: none;}
</style>
<script type="text/javascript">
   function reloadCode(){
	   document.getElementById("imgVerfity").src="verfity?i="+new Date().getTime();
   }
</script>
</head>
<body>
<div style="margin:0 auto;height:24px;line-height:24px;">
	<form action="login" method="post">
	    <input type="text" placeholder="请输入验证码..." name="verfityCode">
		<a href="javascript:;" onclick="reloadCode();"><img alt="验证码" src="verfity" id="imgVerfity">看不清,点一下</a><br/>
		<input type="submit" value="提交"/>
	</form>
</div>
</body>
</html>