﻿为什么需要验证吗？
    用户注册,登录(防止注册机和恶意攻击),论坛(防止灌水)

本质：
    图文并茂的一种形式,利用java技术生成一张图片,图片中有文字,数字,字母,表达式等等
    把这些图片和数字,字母、表达式等等,最终反正session中,
     用户在前端输入之后会与session中的验证码进行比对

PS：如果需要确保数据的安全性,必须要做前台js验证和服务端验证（比如涉及到金钱方面）

验证:
    为了判断数据有效性和合法性,前期不要花太多时间考虑,安全
    
应用场景:
	#防止论坛灌水--防止垃圾灌水
	#防止恶意发送请求
	#防止注册机

验证码种类:
	1.纯数字  -->4539
	2.纯字母  -->zsdc
	3.纯中文  -->中国人民
	4.字母+数字   -->x2c3
	5.数字计算公式 -->1+1=?  
	
#有时候可能还会跟短信验证码结合,手机发送一些中文,页面上出现九宫格,让用户选择<百度贴吧>
    