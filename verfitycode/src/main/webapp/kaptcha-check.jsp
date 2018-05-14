<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kaptcha验证码验证</title>
</head>
<body>
    <%
        //从session中取服务器中的验证码
        String kaptchaServer = (String)request.getSession().getAttribute
        (com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String kaptchaClient = request.getParameter("checkCode");
        out.print("<div style='text-align: center;'>");
        if (kaptchaServer.equals(kaptchaClient))
            out.print("<h2>verify success</h2>");
        else
            out.println("<h2>verify fail</h2>");

        out.print("<h4>kaptchaServer===><span style='color:red;'>"+kaptchaServer+"</span>\tkaptchaClient===><span style='color:red;'>"+kaptchaClient+"</span></h4>");
        out.print("</div>");
    %>
</body>
</html>