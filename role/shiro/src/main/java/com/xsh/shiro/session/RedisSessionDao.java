package com.xsh.shiro.session;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.SerializationUtils;

import com.xsh.shiro.util.JedisUtil;

public class RedisSessionDao extends AbstractSessionDAO {

	@Resource(name="jedisUtil")
	private JedisUtil jedisUtil;
	
	private static final String SESSION_PREFIX="xsh:session:";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		saveSession(session);
	}

	@Override
	public void delete(Session session) {
		if(null == session || session.getId() == null) {
			return;
		}
		
		byte[] sessionIdKey = getKey(session.getId());
		jedisUtil.del(sessionIdKey);
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<String> keys = jedisUtil.keys(SESSION_PREFIX);
		
		Set<Session> sessions = new HashSet<Session>(1);
		if(CollectionUtils.isEmpty(keys)) {
			return sessions;
		}
		for (String key : keys) {
			String sessionValue = jedisUtil.get(key);
			Session session = (Session) SerializationUtils.deserialize(sessionValue.getBytes());
			sessions.add(session);
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println("do ReadSession...");
		if(null == sessionId) {
			return null;
		}
		
		byte[] sessionIdKey = getKey(sessionId);
		byte[] sessionValue = jedisUtil.get(sessionIdKey);
		if(null == sessionValue) {
			return null;
		}
		
		Session session = null;
		try {
			session = (Session) SerializationUtils.deserialize(sessionValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	
	private void saveSession(Session session) {
		if(null != session && null != session.getId()) {
			byte[] sessionIdKey = getKey(session.getId());
			try {
				jedisUtil.set(sessionIdKey,SerializationUtils.serialize(session));
				jedisUtil.expire(sessionIdKey, 3600);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private byte[] getKey(Serializable sessionId) {
		return (SESSION_PREFIX + sessionId).getBytes();
	}

}
