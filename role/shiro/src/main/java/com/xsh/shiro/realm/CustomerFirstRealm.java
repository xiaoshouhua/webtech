package com.xsh.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

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
import com.xsh.shiro.dao.UserDao;
import com.xsh.shiro.vo.User;

public class CustomerFirstRealm extends AuthorizingRealm {
	
	@Resource(name="userDao")
	private UserDao userDao;

	//2.授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		//1.获取用户名
		String userName = (String)principals.getPrimaryPrincipal();
		
		//2.从数据库或者缓存中获取角色数据
		Set<String> roles = getRolesByUserName(userName);
		Set<String> permissions = getPermissionsByRoles(roles);
		
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
		System.out.println("do Read DB 1...");
		List<String> roles = userDao.getRolesByUserName(userName);
		return new HashSet<String>(roles);
	}

	//模拟数据库凭证,可替换为从数据库或缓存中获取
	private Set<String> getPermissionsByRoles(Set<String> roles) {
		System.out.println("do Read DB 2...");
		return new HashSet<String>(userDao.getPermissionsByRoles(new ArrayList<String>(roles)));
	}

	private String getPasswordByUserName(String userName) {
		System.out.println("do Read DB 3...");
		//从数据库获取
		User user = userDao.getPasswordByUserName(userName);
		if(null != user) {
			return user.getPassword();
		}
		return null;
	}
	
	public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toString());
        
        //【加盐】
        Md5Hash md5Hash2 = new Md5Hash("123456","xiaoshouhua");
        System.out.println(md5Hash2.toString());
    }

}

