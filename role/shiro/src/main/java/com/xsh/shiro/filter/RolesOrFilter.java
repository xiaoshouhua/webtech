package com.xsh.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * ##1011
 * 自定义一个鉴权过滤器,只要有一种角色就放行
 * @author shouhua.xiao
 *
 */
public class RolesOrFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj)
			throws Exception {
		
		Subject subject = getSubject(request, response);
		
		String[] roles = (String[]) obj;
		if(null == roles || roles.length == 0) {
			return true;
		}
		
		//只有有一种角色就放行
		for (String role : roles) {
			if(subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

}
