<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dwr message push</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/dwr/util.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/dwr/engine.js"></script>
    <script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/Hello.js"></script>
    <script type="text/javascript">
        function firstDwr(){
                service.sayHello("Jorwen",callBackHello)
        }

        function callBackHello(data){
            alert(data);
        }

    </script>
</head>
<body>
<input type="button" value="测试按钮" onclick="firstDwr()"/>
</body>
</html>
