#### 什么是Shiro?

	Apache的强大灵活的开源安全框架

#### Shiro作用:
	
	提供认证、授权、企业会话管理、安全加密的功能.
	
#### Shiro认证过程

	1.创建SecurityManager
	2.主体提交认证
	3.SecurityManager认证
	4.Authenticator认证
	5.Realm验证

#### Shiro授权过程

	1.创建SecurityManager
	2.主体提交授权
	3.SecurityManager授权
	4.Authorizer授权
	5.Realm获取角色权限数据(一般从数据库或Redis中获取)
	
#### Shiro自定义Realm

	内置Realm：
	
		iniRealm：
		jdbcRealm：	
		
	自定义Realm：
		
		
				