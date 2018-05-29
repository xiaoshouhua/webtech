package com.xsh.sso.crossdomain;

public class CrossSSOCheck {
	
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
	
	public static boolean checkCookie(String cookieName,String cookieValue) {
		if(null != cookieName) {
			if(CrossSSOCheck.COOKIENAME.equals(cookieName)) {
				//可能需要cookie值的验证+结合数据库或redis
				return true;
			}
		}
		return false;
	}
			
}

