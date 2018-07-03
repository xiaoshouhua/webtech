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
	    
	3.配置bean上下文
		
		<!-- 设置 Shiro 加盐策略 -->
	    <bean id="credentialsMatcher" class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
	        <property name="hashAlgorithmName" value="md5" />
	        <property name="hashIterations" value="1" />
	    </bean>
	
		<bean id="realm" class="com.xsh.shiro.realm.CustomerFirstRealm">
			<property name="credentialsMatcher" ref="credentialsMatcher" />
		</bean>
	
		<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
			<property name="realm" ref="realm"></property>
		</bean>
	
		<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
			<property name="securityManager" ref="securityManager"></property>
			<property name="loginUrl" value="login.html"></property>
			<property name="unauthorizedUrl" value="403.html"></property>
			<property name="successUrl" value="index.jsp"></property>
			<!-- 设置链接权限 -->
			<property name="filterChainDefinitions">
				<value>
					/login.html = anon
	                  /login = anon
					/subLogin = anon
					<!-- 
	                                                  设置所有的URL都必须要认证过后才可以登录 
	                    *：用于匹配零个或多个字符串
	                    **：用于匹配路径中的零个或多个路径   
	                -->
					/** = authc
				</value>
			</property>
		</bean>