package com.xsh.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * TagSupport之自定义属性
 * @author shouhua.xiao
 *
 */
public class DBConnectionTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String driver;
	private String url;;  
	private String username;  
    private String password;
    private String sql;
    
    
	@Override
	public int doEndTag() throws JspException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		
		try {
			Class.forName(this.driver);
	    	//建立连接  
	    	conn = DriverManager.getConnection(url,username,password);
			System.out.println("动态加载mysql驱动成功!");

			pstmt = conn.prepareStatement(this.sql);
			
			rs =  pstmt.executeQuery();
			
			if(null != rs) {
				while(rs.next()) {
					pageContext.getOut().print(rs.getString("id")+"|"+rs.getString("name")+"|"+rs.getString("nicename")+"<br/>");
				}
			}
		} catch (SQLException e) {
			System.err.println("保存失败");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			closeConnection(rs, pstmt, conn);
		}
		return super.doEndTag();
	}
	
	private void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}

}
