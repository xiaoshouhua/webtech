package com.xsh.sso.crossdomain.x;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.xsh.sso.crossdomain.CrossSSOCheck;

@Controller
@RequestMapping("/cross")
public class DemoXController {

	@RequestMapping("/doLogin")
	public void doLogin(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "gotoUrl", required = false) String gotoUrl,
			HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			boolean flag = CrossSSOCheck.checkLogin(username, password);
			if(flag) {
				out.print("1");
				out.close();
			}
			out.print("0");
			out.close();
		} catch (IOException e) {
			System.err.println("验证用户名密码失败!message is "+e);
		}
	}
	
	@RequestMapping("/checkCookie")
	public void checkCookie(@RequestParam(value = "cookieName", required = false) String cookieName,
			@RequestParam(value = "cookieValue", required = false) String cookieValue,
			HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			boolean flag = CrossSSOCheck.checkCookie(cookieName, cookieValue);
			if(flag) {
				out.print("1");
				out.close();
			}
			out.print("0");
			out.close();
		} catch (IOException e) {
			System.err.println("验证Cookie失败!message is "+e);
		}
	}
	
}
