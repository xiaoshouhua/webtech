<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>VerifyCode</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript">
        var path="<%=path%>";
        function reloadCode(){
            document.getElementById("codeImg").src= path + '/jcaptcha.jpg?nocache='+new Date().getTime();
        }
    </script>
  </head>
<body style="text-align: center;">
<h2>Hello World!</h2>
<form action="jcaptcha/submit" method="post">
<span style="margin: 10px 0 0 10px;vertical-align: baseline;">
请输入验证码：<input name="verifyCode" type="text" /></span><img id="codeImg" style="vertical-align: bottom;margin: 10px 0 0 10px;cursor: pointer;"  alt="验证码" src="<%=path%>/jcaptcha.jpg" onclick="reloadCode();"><br/>
<input type="submit" value="提交"/>
</form>
</body>
</html>