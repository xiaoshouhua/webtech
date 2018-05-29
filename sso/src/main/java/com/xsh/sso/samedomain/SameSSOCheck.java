package com.xsh.sso.samedomain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SameSSOCheck {
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	public  static final String COOKIENAME = "ssocookie";
	//private static final String COOKIENAME_PREFIX = "";

	/**
	 * 验证用户名和密码
	 * @param username 
	 * @param password
	 * @return 验证结果
	 */
	public static boolean checkLogin(String username,String password) {
		if(USERNAME.equals(username) && PASSWORD.equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(null != cookies) {
			for (Cookie cookie : cookies) {
				if(SameSSOCheck.COOKIENAME.equals(cookie.getName())) {
					//可能需要cookie值的验证+结合数据库或redis
					return true;
				}
			}
		}
		return false;
	}
			
}

