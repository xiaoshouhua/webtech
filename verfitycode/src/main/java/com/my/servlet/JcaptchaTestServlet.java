package com.my.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

@WebServlet(name = "JcaptchaTestServlet",urlPatterns="/jcaptcha/submit")
public class JcaptchaTestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String codeValue = request.getParameter("verifyCode");
		boolean checkRs = SimpleImageCaptchaServlet.validateResponse(request, codeValue);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write("<html><body>");
		if (checkRs) {
			response.getWriter().write("验证成功!");
		} else {
			response.getWriter().write("验证失败!");
		}
		response.getWriter().write("<br/><a href='javascript:history.go(-1);'>再试一次</a>");
		response.getWriter().write("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
