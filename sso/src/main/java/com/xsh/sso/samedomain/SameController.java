package com.xsh.sso.samedomain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/same")
public class SameController {

	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "gotoUrl", required = false) String gotoUrl,
			Model model,HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean flag = SameSSOCheck.checkLogin(username, password);
		if(flag) {
			Cookie cookie = new Cookie(SameSSOCheck.COOKIENAME,"sso");//后面参数可以采用加密算法进行加密
			cookie.setPath("/");
			response.addCookie(cookie);
			return "redirect:"+gotoUrl;
		}
		model.addAttribute("gotoUrl", gotoUrl);
		return "same/login";
	}
	
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request,Model model) {
		System.out.println("成功访问!hello");
		boolean flag = SameSSOCheck.checkCookie(request);
		if(flag) {
			return "same/hello";
		}else {
			String path = request.getContextPath();
			String gotoUrl = request.getRequestURI().replaceAll(path, "");
			model.addAttribute("gotoUrl", gotoUrl);
			return "same/login";
		}
		
	}
	
	@RequestMapping("/hell")
	public String hell(HttpServletRequest request,Model model) {
		System.out.println("成功访问!hell");
		boolean flag = SameSSOCheck.checkCookie(request);
		if(flag) {
			return "same/hell";
		}else {
			String path = request.getContextPath();
			String gotoUrl = request.getRequestURI().replaceAll(path, "");
			model.addAttribute("gotoUrl", gotoUrl);
			return "same/login";
		}
	}
	
}
