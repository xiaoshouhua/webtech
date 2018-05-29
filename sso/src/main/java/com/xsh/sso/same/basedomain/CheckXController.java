package com.xsh.sso.same.basedomain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/samebase")
public class CheckXController {

	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "gotoUrl", required = false) String gotoUrl,
			Model model,HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean flag = SameBaseSSOCheck.checkLogin(username, password);
		if(flag) {
			Cookie cookie = new Cookie(SameBaseSSOCheck.COOKIENAME,"sso");//后面参数可以采用加密算法进行加密
			//关键：同父域设置
			cookie.setDomain(".x.com");//设置到父域
			cookie.setPath("/");//设置到顶层
			response.addCookie(cookie);
			return "redirect:"+gotoUrl;
		}
		model.addAttribute("gotoUrl", gotoUrl);
		return "same/login";
	}
	
}
