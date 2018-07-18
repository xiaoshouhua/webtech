
### XSS的定义和原理
    通常指黑客通过“HTML注入”篡改了网页,插入恶意脚本，从而在用户浏览网页时，控制用户浏览器的一种攻击.
    
### XSS的攻击方式
	1.反射型
	    发出请求时，XSS代码会出现在URL中，作为输入提交到服务器端，服务器端解析后响应，
	    XSS代码随响应内容一起传回给浏览器，最后浏览器解析执行XSS代码。
	    这个过程像一次反射，故叫反射型XSS.
 		
	2.存储型：
	    存储型XSS和反射型的差别仅在于，提交的代码会存储在服务器端（数据库，内存，文件系统等），
	    下次请求目标页面时不用再提交XSS代码.
 	
### XSS的防御措施
	1.编码
	    字符|十进制|转义字符
	      "|&#34;|&quot;
	      &|&#38;|&amp;
	      <|&#60;|&lt;
	      >|&#62;|&gt;
	 	     不断开空格(non-breaking space)|&#160;|&nbsp;
		 
	2.过滤 
	    移除用户上传的DOM属性,如onerror等，
	    移除用户上传的style节点，script节点，iframe节点等
		
	3.校正
	    避免直接对HTML Entity解码
	    使用DOM Parse转换,校正不配对的DOM标签.