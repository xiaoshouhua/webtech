package com.xsh.shiro.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
			subject.login(token);
		} catch (AuthenticationException e) {
			logger.error("登录失败!",e);
			return "登录失败!"+e.getMessage();
		}
		//4.验证登录结果
		System.out.println("是否认证成功:"+subject.isAuthenticated());
		return "登录成功!";
	}
	
}
