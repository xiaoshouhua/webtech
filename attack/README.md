### Web攻防

#### 一.常见Web攻击类型
    1.SQL注入 
    2.跨站脚本攻击（XSS）
    3.上传任意文件 
    4.撞库攻击

##### 1.SQL注入
    漏洞描述：
        脱裤、利用注入点提取控制主机  
    
    修复建议：
        转义特殊字符如单双引号

##### 2. 跨站脚本攻击（XSS，Cross Site Script）
    通常指黑客通过“HTML注入”篡改
    了网页,插入恶意脚本，从而在用户浏览网页时，控制用户浏览器的一种攻击.
    
    XSS漏洞可被用于用户身份窃取（特别是管理员）、行为劫持、挂马、蠕虫
    钓鱼等。XSS是目前客户端Web安全中最重要的漏洞.
        
    XSS按效果的不同可以分为3种：
    
        a.反射XSS:页面仅把用户输入直接回显在页面或源码中，需要诱使用户点击才能成功.
        
        b.存储XSS：CSS攻击代码会被存储在服务器上,由于用户可能会主动浏览被攻击页面，此种
        方法危害较大
        
        c.DOM XSS:通过修改页面的DOM节点形成XSS，严格来说也可化为反馈型XSS.
        
    XSS的修复建议:
        1、检查所有用户输入的输出点。
           因为XSS最终攻击是发生在输出点,因此需要
           分析出用户输入数据的所有输出点的环境，是输入在HTML标签中，还是HTML属性
           script标签、事件、CSS位置中，针对不同的输出位置，制定不同的转义或过滤规则.
        
        2.处理富文本.
           在文章、论坛等需要用到富文本的地方，需要特别注意富文本与XSS的区别，严格禁止所有的危险标签及"事件",
           原则上应当使用白名单过滤标签，事件及属性.

##### 3.上传任意文件
    漏洞描述：
        可能导致执行恶意代码  
    
    修复建议：
        根据业务需求，如只允许上传图片，就应限制上传内容格式，如jpg jpeg等
        
##### 4.撞库攻击
    
    漏洞描述：
        可以使用常用用户名和密码或者之前已泄露的数据库来对站点进行撞库攻击，登录他人账号。 
    
    修复建议：
        对高频率的登录或查看用户是否存在请求做限制，强制要求输入验证码
