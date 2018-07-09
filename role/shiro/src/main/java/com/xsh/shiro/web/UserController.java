package com.xsh.shiro.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsh.shiro.vo.User;

@Controller
public class UserController {
	
	Log logger = LogFactory.getLog(UserController.class);

	@RequestMapping(value="/subLogin",produces="application/json;charset=utf-8")
	@ResponseBody
	public String subLogin(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			//是否记住我
			token.setRememberMe(user.isRememberMe());
			subject.login(token);
		} catch (AuthenticationException e) {
			logger.error("登录失败!",e);
			return "登录失败!"+e.getMessage();
		}
		
		System.out.println("是否认证成功:"+subject.isAuthenticated());
		System.out.println("是否拥有admin角色:"+subject.hasRole("admin"));		
		if(!subject.hasRole("admin")) {
			return "登录失败!没有admin角色!";
		}
		
		try {
			subject.checkPermission("update");
		} catch (AuthorizationException e) {
			return "登录失败!拥有admin角色但不拥有修改权限!"+e.getMessage();
		}
		return "登录成功!拥有admin角色且拥有修改权限";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value="/testRole",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testRole() {
		return "testRole success!";			
	}
	
	//不具有system角色时会抛出异常
	@RequiresRoles("system")
	@RequestMapping(value="/testUnRole",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testUnRole() {
		return "testRole success!";
	}
	
	@RequiresPermissions("update")
	@RequestMapping(value="/testPermission",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testPermission() {
		return "testPermission success!";
	}
	
	//不具有add权限时会抛出异常
	@RequiresPermissions("add")
	@RequestMapping(value="/testUnPermission",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testUnPermission() {
		return "testUnPermission success!";
	}
	
	@RequestMapping(value="/testPerms",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testPerms() {
		return "testPerms success!";
	}
	
	@RequestMapping(value="/testUnPerms",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testUnPerms() {
		return "testUnPerms success!";
	}
	
	@RequestMapping(value="/testRolesOr",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testRolesOr() {
		return "testRolesOr success!";
	}
	
}
