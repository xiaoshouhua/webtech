package com.xsh.shiro.realm;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomerFirstRealm extends AuthorizingRealm {
	
	Map<String,String> userMap = new ConcurrentHashMap<String,String>();
	{
		//userMap.put("xiaoshouhua", "e10adc3949ba59abbe56e057f20f883e");
		//【加盐】
		userMap.put("xiaoshouhua", "6e5fc47014927795ea6ddc2567c3bd44");
		userMap.put("root", "fcea920f7412b5da7be0cf42b8c93759");
		userMap.put("admin", "admin");
		super.setName("customerRealm");
	}

	//2.授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//1.获取用户名
		String userName = (String)principals.getPrimaryPrincipal();
		
		//2.从数据库或者缓存中获取角色数据
		Set<String> roles = getRolesByUserName(userName);
		Set<String> permissions = getPermissionsByUserName(userName);
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	//1.认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//1.从主体传过来的认证信息中,获取用户名
		String userName = (String)token.getPrincipal();
		
		//2.主体提交认证请求
		//通过用户名到数据库中获取凭证
		String password = getPasswordByUserName(userName);
		if(null == password) {	
			return null;
		}
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,super.getName());
		//【加盐】
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
		
		return authenticationInfo;
	}
	
	//模拟数据库凭证,可替换为从数据库或缓存中获取
	private Set<String> getRolesByUserName(String userName) {
		Set<String> roles = new HashSet<String>();
		roles.add("admin");
		roles.add("user");
		return roles;
	}

	//模拟数据库凭证,可替换为从数据库或缓存中获取
	private Set<String> getPermissionsByUserName(String userName) {
		Set<String> permissions = new HashSet<String>();
		permissions.add("admin:edit");
		permissions.add("admin:delete");
		permissions.add("admin:view");
		permissions.add("user:edit");
		permissions.add("user:view");
		return permissions;
	}

	private String getPasswordByUserName(String userName) {
		//TODO 可替换为从数据库或缓存中获取
		return userMap.get(userName);
	}
	
	public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toString());
        
        //【加盐】
        Md5Hash md5Hash2 = new Md5Hash("123456","xiaoshouhua");
        System.out.println(md5Hash2.toString());
    }

}

