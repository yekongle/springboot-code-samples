package top.yekongle.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.login.dto.UserDTO;
import top.yekongle.login.entity.User;
import top.yekongle.login.exception.UserAlreadyExistException;
import top.yekongle.login.service.UserService;
import top.yekongle.login.util.GenericResponse;

/**
* @Author: Yekongle 
* @Date: 2020年5月5日
*/
@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired UserService userService;
	
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("formTitle", "注册");
		return "registration";
	}
	 
	
	@PostMapping("/registration")
	@ResponseBody
	public GenericResponse registerUserAccount(@Valid UserDTO userDTO) {
		User registered = userService.registerNewUserAccount(userDTO);
		
		return new GenericResponse("success");
	}
}
