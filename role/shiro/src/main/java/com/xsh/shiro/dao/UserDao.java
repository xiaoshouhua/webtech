package com.xsh.shiro.dao;

import java.util.List;

import com.xsh.shiro.vo.User;

public interface UserDao {

	User getPasswordByUserName(String userName);
	
	List<String> getRolesByUserName(String userName);
	
	List<String> getPermissionsByRoles(List<String> roles);
	
}
