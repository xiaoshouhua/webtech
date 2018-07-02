package com.xsh.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IniRealmTest {

	IniRealm realm = null;
	
	Subject subject = null;

	@Before
	public void setUp() throws Exception {
		realm = new IniRealm("classpath:user.ini");
		
		realm.addAccount("xiaoshouhua", "123456","admin");
		
		//1.创建SecurityManager
		DefaultSecurityManager securityManager =  new   DefaultSecurityManager();
		securityManager.setRealm(realm);
		
		//2.主体提交认证/授权
		SecurityUtils.setSecurityManager(securityManager);
		subject = SecurityUtils.getSubject();
	}

	@After
	public void tearDown() throws Exception {
		//注销登录
		subject.logout();
	}

	@Test
	public void testAuthenticated() {
		
		//3.开始登录
		//账户不存在抛出异常:org.apache.shiro.authc.UnknownAccountException: Realm [org.apache.shiro.realm.SimpleAccountRealm@7b1d7fff] was unable to find account data for the submitted AuthenticationToken [org.apache.shiro.authc.UsernamePasswordToken - root, rememberMe=false].
		//UsernamePasswordToken token = new UsernamePasswordToken("root","1234567");
		
		//账户密码不正确抛出异常:org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - xiaoshouhua, rememberMe=false] did not match the expected credentials.
		//UsernamePasswordToken token = new UsernamePasswordToken("xiaoshouhua","1234567");
		
		UsernamePasswordToken token = new UsernamePasswordToken("xiaoshouhua","123456");
		subject.login(token);
		
		//4.验证登录结果
		System.out.println("是否认证成功:"+subject.isAuthenticated());
	}
	
	@Test
	public void testAuthorizer() {
		
		UsernamePasswordToken token = new UsernamePasswordToken("xiaoshouhua","123456");
		subject.login(token);
		
		//4.1 验证登录结果
		System.out.println("是否认证成功:"+subject.isAuthenticated());
		//4.2 验证授权结果
		subject.checkRole("admin");
	}

}
