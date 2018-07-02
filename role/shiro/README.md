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
		1.继承AuthorizingRealm
		2.重写doGetAuthenticationInfo(认证),doGetAuthorizationInfo(授权)方法 
		
		
####　Shiro散列配置 
	1.HashedCredentialsMatcher 
	2.自定义Realm中使用散列 
	3.盐的使用            	
	
### Shiro集成Spring
	
	1.增加jar依赖
	    
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.4.0</version>
        </dependency>
        
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-web</artifactId>
            <version>1.4.0</version>
        </dependency>
        
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.0</version>
        </dependency>
	    
	2.增加shiro过滤器
	    
        <!-- 设置 Shiro 的过滤器 -->
	    <filter>
	        <filter-name>shiroFilter</filter-name>
	        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	    </filter>
	    <filter-mapping>
	        <filter-name>shiroFilter</filter-name>
	        <url-pattern>/*</url-pattern>
	    </filter-mapping>	
	    
	3.增加shiro过滤器