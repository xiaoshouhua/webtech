package com.xsh.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**
 * 自定义一个认证过滤器
 * @author shouhua.xiao
 *
 */
public class UserFilter extends AuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//TODO 待实现
		return false;
	}

}
