package com.xsh.sso.same.basedomain;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/samebase/demo2")
public class Demo2XController {
	
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request,Model model) {
		System.out.println("成功访问!hello");
		boolean flag = SameBaseSSOCheck.checkCookie(request);
		if(flag) {
			return "samebase/hello2";
		}else {
			String path = request.getContextPath();
			String gotoUrl = request.getRequestURI().replaceAll(path, "");
			model.addAttribute("gotoUrl", gotoUrl);
			return "samebase/login";
		}
		
	}
}
