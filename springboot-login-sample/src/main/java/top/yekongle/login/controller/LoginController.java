package top.yekongle.login.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
* @Author: Yekongle 
* @Date: 2020年5月5日
*/

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// 如果已经登录则跳转到 home 页面
		if (auth instanceof AnonymousAuthenticationToken) {
			return "login";
		} else {
			return "home";
		}
	}
}
