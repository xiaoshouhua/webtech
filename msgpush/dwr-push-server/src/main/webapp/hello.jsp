<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dwr message push</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
    <script type="text/javascript" src="/dwr-push-server/js/dwr/util.js"></script>
    <script type="text/javascript" src="/dwr-push-server/js/dwr/engine.js"></script>
    <script type="text/javascript" src="/dwr-push-server/js/dwr/interface/DwrPush.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		//激活反转
    		dwr.engine.setActiveReverseAjax(true);
    		
    		$("#btn_submit").click(function(){
    			DwrPush.send($("#msg").val());
    		});
    	});
    
        function callback(msg){
            $("#ul").html($("#ul").html()+"<br/>"+msg);
        }

    </script>
</head>
<body>
<ul id="ul" style="color:red;font-size:60px;"></ul>

<textarea rows="5" cols="40" id="msg"></textarea>
<input type="button" id="btn_submit" value="测试按钮"/>
</body>
</html>
