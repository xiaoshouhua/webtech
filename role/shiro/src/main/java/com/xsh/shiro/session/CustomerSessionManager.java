package com.xsh.shiro.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

/**
 * 自己实现一个SessionManager,避免频繁访问redis,只取一遍
 * CustomerSessionManager
 * @author Administrator
 *
 */
public class CustomerSessionManager extends DefaultWebSessionManager {

	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey);
		ServletRequest request = null;
		if(sessionKey instanceof WebSessionKey) {
			request = ((WebSessionKey)sessionKey).getServletRequest();
		}
		if(request != null && sessionId != null) {
			Session session =  (Session) request.getAttribute(sessionId.toString());
			if(null != session) {
				return session;
			}
		}
		Session session = super.retrieveSession(sessionKey);
		if(null != session) {
			request.setAttribute(sessionId.toString(),session);
		}
		return session;
	}

}
