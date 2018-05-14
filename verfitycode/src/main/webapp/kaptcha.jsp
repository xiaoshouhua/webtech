<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kaptcha生成验证码</title>
<script type="text/javascript">
    function changeR(node){
        // 用于点击时产生不同的验证码
        node.src = "randomcode.jpg?time="+new Date().getTime() ;    
    }
</script>
</head>
<body>
<div style="text-align: center;">
<h2>验证码之kaptcha</h2>
<img alt="random" src="randomcode.jpg" onclick="changeR(this)" style="cursor: pointer;"><br/><br/>
    <form action="kaptcha-check.jsp">
        <input type="text" name="checkCode">
        <input type="submit" value="提交">
    </form> 
</div>
</body>
</html>