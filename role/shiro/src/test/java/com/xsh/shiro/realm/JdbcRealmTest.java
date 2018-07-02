package com.xsh.shiro.realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcRealmTest {

	JdbcRealm realm = null;
	Subject subject = null;
	
	protected static final String AUTHENTICATION_QUERY = "select password from u_user where username = ?";
	protected static final String USER_ROLES_QUERY = "select r.code from u_role r where r.id in (select ur.rid from u_user_role ur,u_user u where ur.uid = u.id and u.username = ?)";
	protected static final String PERMISSIONS_QUERY = "select up.name from u_permission up where up.id in (select rp.pid from u_role_permission rp ,u_role r where r.id = rp.rid and r.code = ?)";
	
	DruidDataSource dataSource = new DruidDataSource();
	{
		dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
		dataSource.setUsername("root");
		dataSource.setPassword("root123456");
	}
	
	@Before
	public void setUp() throws Exception {
		realm = new JdbcRealm();
		realm.setDataSource(dataSource);
		//设置为true才会查询权限数据		
		realm.setPermissionsLookupEnabled(true);
		
		//认证sql
		realm.setAuthenticationQuery(AUTHENTICATION_QUERY);
		realm.setUserRolesQuery(USER_ROLES_QUERY);
		realm.setPermissionsQuery(PERMISSIONS_QUERY);
		
		//1.创建SecurityManager
		DefaultSecurityManager securityManager =  new  DefaultSecurityManager();
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
