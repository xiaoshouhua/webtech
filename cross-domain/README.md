#### 为什么会发送跨域？
    1.浏览器限制 
    2.跨域 
    3.XHR(XMLHttpRequest)请求



#### 跨域解决方案
    1、浏览器禁止检查
       -disable-web-
        
    2、JSONP
       是一个非官方协议,一种约定,前后端参数名
       前后台代码都需要改动
    
       弊端：服务器需要改动代码支持
             只支持GET
             发送的不是XHR请求 
        
    3.被调用方解决（直接从浏览器发送过去）
        1.服务器端实现 
        2.nginx配置
        
        filter实现：
            HttpServletResponse.addHeader("Access-Control-Allow-Origin","*");带cookie的时候,origin必须是全匹配,不能使用*
            HttpServletResponse.addHeader("Access-Control-Allow-Methods","*")
            
            //预检命令
            HttpServletResponse.addHeader("Access-Control-Allow-Headers"," Content-Type");
            
            //带cookie必须设置
            HttpServletResponse.addHeader("Access-Control-Allow-Credentials","true");
            
       -------带自定义头的跨域-----------
            1.ajax调用增加headers属性
              headers:{   
                "xxx":"yyy"
              }
            2.ajax调用增加headers属性
            
       nginx实现：
            
            
    4.调用方解决（从服务器转发过去的）
    
    
工作中比较常见的[简单请求]:
    Methods: GET, HEAD, POST
    
    请求 header 里面:
        
    * 无自定义头
        
    * Content-Type 为以下几种:
        
    * text/plain
        
    * multipart/form-data
        
    * application/x-www-form-urlencoded

工作中常见的[非简单请求]:

    * put, Delete 方法的 ajax 请求
    
    * 发送JSON格式的 ajax 请求
    
    * 带自定义头的 ajax 请求


