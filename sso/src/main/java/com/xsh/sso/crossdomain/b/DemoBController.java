package com.xsh.sso.crossdomain.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.xsh.sso.crossdomain.CrossSSOCheck;
import com.xsh.sso.crossdomain.HttpUtil;

@Controller
@RequestMapping("/cross/b")
public class DemoBController {
	
	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "gotoUrl", required = false) String gotoUrl,
			HttpServletRequest request,
			Model model) {
		Map<String, String> paramMap = new HashMap<String,String>();
		
		paramMap.put("username", username);
		paramMap.put("password", password);
		
		String result = HttpUtil.sendGet("http://www.x.com:8081/sso/cross/doLogin.html", paramMap);
		if("1".equals(result)) {
			List<String> hiddenUrls = new ArrayList<String>();
			hiddenUrls.add("http://www.a.com:8081/sso/cross/a/addCookie.html");
			hiddenUrls.add("http://www.b.com:8081/sso/cross/b/addCookie.html");
			model.addAttribute("hiddenUrls",hiddenUrls);
			return gotoUrl;
		}
		else {
//			gotoUrl = "cross/hello2";
			model.addAttribute("gotoUrl",getPath(request));
			model.addAttribute("path","sso/cross/b");
			model.addAttribute("gotoUrl",gotoUrl);
			return "cross/login";
		}
	}
	
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request,Model model) {
		System.out.println("成功访问!hello"+request.getRequestURI());
		boolean flag = false;
		if(null != request.getCookies()) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				Map<String, String> paramMap = new HashMap<String,String>();
				paramMap.put("cookieName", cookie.getName());
				paramMap.put("cookieValue", cookie.getValue());
				String result = HttpUtil.sendGet("http://www.x.com:8081/sso/cross/checkCookie.html", paramMap);
				if("1".equals(result)) {
					flag = true;
					break;
				}
			}
		}
		if(flag) {
			return "cross/hello2";
		}else {
			model.addAttribute("path","sso/cross/b");
			model.addAttribute("gotoUrl","cross/hello2");
			return "cross/login";
		}
	}
	
	@RequestMapping("/addCookie")
	public void addCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(CrossSSOCheck.COOKIENAME,"sso");//后面参数可以采用加密算法进行加密
		cookie.setPath("/");//设置到顶层
		response.addCookie(cookie);
	}
	
	public String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String gotoUrl = request.getRequestURI().replaceAll(path, "");
		return gotoUrl;
	}
	
}
