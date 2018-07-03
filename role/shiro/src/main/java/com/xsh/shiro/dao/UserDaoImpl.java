package com.xsh.shiro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.xsh.shiro.vo.User;

@Component(value="userDao")
public class UserDaoImpl implements UserDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public User getPasswordByUserName(String userName) {
		
		String sql = "select id,user_name,password from t_user where user_name = ?"; 
		List<User> list = jdbcTemplate.query(sql, new String[] {userName},new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		return list.get(0);
	}

	@Override
	public List<String> getRolesByUserName(String userName) {
		
		String sql = "select role_name from t_roles where user_name = ? ";
		
		return jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("role_name");
			}
		});
	}

	@Override
	public List<String> getPermissionsByRoles(List<String> roles) {
		
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("roles",roles);
		
		String sql = "select permission from t_permissions where role_name  in (:roles) ";
		return namedParameterJdbcTemplate.query(sql, params, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("permission");
			}
		});
	}

}
