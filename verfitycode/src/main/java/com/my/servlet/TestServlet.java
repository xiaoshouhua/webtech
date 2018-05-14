package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="TestServlet",urlPatterns="/login")
public class TestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String verfityCode = request.getParameter("verfityCode");
//		String sverfity = (String) request.getSession().getAttribute("verfityCode");
		String sverfity = (String) request.getSession().getAttribute("verfity_code");
		PrintWriter pw = response.getWriter();
		if(null == verfityCode) {
			pw.println("验证码不能为空!");
		}
		else {
			if(sverfity.equalsIgnoreCase(verfityCode)) {
				pw.println("success!");
			}else {
				pw.println("failed!");
			}
		}
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
