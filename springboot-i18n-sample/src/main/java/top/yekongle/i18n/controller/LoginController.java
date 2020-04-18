package top.yekongle.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Author: Yekongle 
* @Date: Mar 22, 2020
*/
@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		System.out.println("Test");
		return "login";
	}
}
