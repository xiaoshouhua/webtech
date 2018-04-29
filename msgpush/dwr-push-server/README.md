### Dwr实现Java服务器端向客户端推送消息

#### A.什么是Dwr?
    
        1.是一个基于Ajax的框架 
        2.动态把Java类生成JavaScript  
        3.让客户端JavaScript通过DWR访问Java程序

#### B.Dwr运行原理
    
    1.读取dwr.xml生成相应的xxx.js 
     
    2.js触发xxx.js中的方法 
      
    3.web容器接收请求创建实例
      
    4.调用方法处理返回
   

### C. scriptSession 
    
    创建:每次访问创建一个scriptSession
                                                
    
    Dwr3.0:
        Collection<ScriptSession> sessions = Browser.getTargetSessions(); 
    
    Dwr2.x:
        Collection pages = webContext.getScriptSessionsByPage("/xxx.jsp");
    
### D.Dwr配置详解

    1.拷贝dwr.jar到项目的lib中
    
    2.配置web.xml
        <servlet>   
            <servlet-name>dwr-invoker</servlet-name>   
            <!-- 以下一行代码为Dwr2.x配置 -->
            <servlet-class>org.directwebremoting.servlet.DwrServlet</servlet-class>
            <!-- 以下一行注释代码为Dwr3.0配置 -->
            <!--<servlet-class>uk.ltd.getahead.dwr.DWRServlet</servlet-class>-->
            <init-param>   
                <param-name>debug</param-name>   
                <param-value>true</param-value>   
            </init-param>
            <!-- 使用服务器推技术(反转Ajax) -->
            <init-param>   
               <param-name>activeReverseAjaxEnabled</param-name>   
               <param-value>true</param-value>   
            </init-param>
            <!-- 使能够从其他域进行请求(true:开启;false:关闭) -->
            <init-param>   
                <param-name>crossDomainSessionSecurity</param-name>   
                <param-value>false</param-value>   
             </init-param>
             <!-- 允许远程JS -->
             <init-param>   
                 <param-name>allowScriptTagRemoting</param-name>   
                 <param-value>true</param-value>   
              </init-param>
        </servlet>
        <servlet-mapping>   
                <servlet-name>dwr-invoker</servlet-name>   
                <url-pattern>/dwr/*</url-pattern>   
        </servlet-mapping>  
     
    3.配置dwr.xml
    
        <?xml version="1.0"encoding="UTF-8"?>
        <!DOCTYPE dwr PUBLIC "-//GetAheadLimited//DTD Direct Web Remoting 2.0//EN""http://getahead.org/dwr/dwr20.dtd">
        <dwr>
            <allow>
               <!-- new和class是写死的,service我们指定这个调用的js的名称,对应的方法在那个类中 -->
               <createcreator="new" javascript="service">
                     <param name="class" value="全路径类">
               </create>
            </allow>
        </dwr>
    
    4.创建一个jsp页面
    
参考： 
	
	 1.http://directwebremoting.org/dwr/
	 2.http://www.cnblogs.com/cyjch/archive/2012/02/16/2353758.html 
	 3.https://www.imooc.com/learn/784   
         
