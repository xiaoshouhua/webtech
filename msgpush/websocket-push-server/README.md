### WebSocket

#### 概述:
	WebSocket是HTML5开始提供的一种浏览器与服务器间进行全双工通讯的网络技术。依靠这种技术可以实现客户端和服务器端的长连接，双向实时通信。
	
#### 特点:
	事件驱动
	异步
	使用ws或者wss协议的客户端socket
	能够实现真正意义上的推送功能

#### 缺点：		
	少部分浏览器不支持，浏览器支持的程度与方式有区别
	
------------------------------------------------------------------
### WebSocket服务器端
#### 概述：
	JSR356定义了WebSocket的规范，Tomcat7中实现了该标准。JSR356 的 WebSocket 规范使用 javax.websocket.*的 API，可以将一个普通 Java 对象（POJO）使用 @ServerEndpoint 注释作为 WebSocket 服务器的端点	
	
------------------------------------------------------------------	
### WebSocket客户端
#### 概述：
	websocket允许通过JavaScript建立与远程服务器的连接，从而实现客户端与服务器间双向的通信。在websocket中有两个方法：　　
　　　   1、send() 向远程服务器发送数据
　　　   2、close() 关闭该websocket链接
　　       
	websocket同时还定义了几个监听函数　　　　
　　　　1、onopen 当网络连接建立时触发该事件
　　　　2、onerror 当网络发生错误时触发该事件
　　　　3、onclose 当websocket被关闭时触发该事件
　　　　4、onmessage 当websocket接收到服务器发来的消息的时触发的事件，也是通信中最重要的一个监听事件。msg.data
　　
	websocket还定义了一个readyState属性，这个属性可以返回websocket所处的状态：
　　　　1、CONNECTING(0) websocket正尝试与服务器建立连接
　　　　2、OPEN(1) websocket与服务器已经建立连接
　　　　3、CLOSING(2) websocket正在关闭与服务器的连接
　　　　4、CLOSED(3) websocket已经关闭了与服务器的连接

　　	websocket的url开头是ws，如果需要ssl加密可以使用wss，当我们调用websocket的构造方法构建一个websocket对象（new WebSocket(url)）的之后，就可以进行即时通信了。	