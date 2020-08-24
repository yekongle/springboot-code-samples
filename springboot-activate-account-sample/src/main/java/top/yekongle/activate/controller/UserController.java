package top.yekongle.activate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import top.yekongle.activate.dto.PasswordDto;
import top.yekongle.activate.dto.UserDTO;
import top.yekongle.activate.entity.PasswordResetToken;
import top.yekongle.activate.entity.User;
import top.yekongle.activate.entity.VerificationToken;
import top.yekongle.activate.event.OnRegistrationCompleteEvent;
import top.yekongle.activate.exception.UserAlreadyExistException;
import top.yekongle.activate.service.UserService;
import top.yekongle.activate.util.GenericResponse;

import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

/**
* @Author: Yekongle 
* @Date: 2020年5月5日
*/
@Slf4j
@Controller
public class UserController {
	
	@Autowired UserService userService;

	@Autowired
	private MessageSource messages;

	@Autowired
	LocaleResolver localeResolver;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	@Autowired
	private UserDetailsService userDetailsService;

	// 注册页面
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("formTitle", "注册");
		return "registration";
	}

	// 用户注册
	@PostMapping("/user/registration")
	@ResponseBody
	public GenericResponse registerUserAccount(@Valid UserDTO userDTO, HttpServletRequest request) {
		User registered = userService.registerNewUserAccount(userDTO);
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
		return new GenericResponse("success");
	}

	// 激活用户账户
	@GetMapping("/registrationConfirm.html")
	public String confirmRegistration(HttpServletRequest request, HttpServletResponse response, RedirectAttributesModelMap model
			, @RequestParam("token") String token) {
		log.info("confirmRegistration");
		Locale locale = localeResolver.resolveLocale(request);
		log.info("token:{}" + token);
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
	        log.info("message:" + message);
			model.addFlashAttribute("errMsg", message);
			return "redirect:/badUser.html";
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addFlashAttribute("message", messages.getMessage("auth.message.expired", null, locale));
			model.addFlashAttribute("expired", true);
			model.addFlashAttribute("token", token);
			return "redirect:/badUser.html";
		}

		user.setEnabled(true);
		userService.saveRegisteredUser(user);
		model.addFlashAttribute("message", messages.getMessage("message.accountVerified", null, locale));

		return "redirect:/login";
	}

	// 重新发送注册令牌
	@GetMapping("/user/resendRegistrationToken")
	@ResponseBody
	public GenericResponse resendRegistrationToken(final HttpServletRequest request, final RedirectAttributesModelMap model, @RequestParam("token") final String existingToken) {
		log.info("resendRegistrationToken");
		final Locale locale = request.getLocale();
		final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
		final User user = userService.getUser(newToken.getToken());
		final SimpleMailMessage email = constructResetVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, user);
		mailSender.send(email);
		log.info("message: {}", messages.getMessage("message.resendToken", null, locale));
		return new GenericResponse(messages.getMessage("message.resendToken", null, locale));
	}

	// 重置密码
	@PostMapping("/user/resetPassword")
	@ResponseBody
	public GenericResponse resetPassword(final HttpServletRequest request, final RedirectAttributesModelMap model
			, @RequestParam("email") final String userEmail) {
		log.info("resetPassword: {}", userEmail);
		final User user = userService.findUserByEmail(userEmail);
		if (user == null) {
			return new GenericResponse(messages.getMessage("message.userNotFound", null, request.getLocale()));
		}

		final String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
	 	final SimpleMailMessage email = constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user);
	 	mailSender.send(email);
		return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
	}

	// 更换密码页面
	@GetMapping("/user/changePassword")
	public String showChangePassword(final HttpServletRequest request, final RedirectAttributesModelMap model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
		log.info("showChangePassword, id:{}, token:{}", id, token);
		final Locale locale = request.getLocale();
		String result = userService.validatePasswordResetToken(token);
		log.info("result:{}", result);
		if(result != null) {
			String message = messages.getMessage("auth.message." + result, null, locale);
			log.info("message:{}", message);
			model.addFlashAttribute("errMsg", message);
			return "redirect:/login.html?";
		} else {
			model.addFlashAttribute("token", token);
			return "redirect:/updatePassword.html";
		}
	}

	@PostMapping("/user/savePassword")
	@ResponseBody
	public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
		log.info("savePassword");
		String result = userService.validatePasswordResetToken(passwordDto.getToken());

		if(result != null) {
			return new GenericResponse(messages.getMessage(
					"auth.message." + result, null, locale));
		}

		Optional<User> user = userService.getUserByPasswordResetToken(passwordDto.getToken());
		if(user.isPresent()) {
			userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
			String message = messages.getMessage(
					"message.resetPasswordSuc", null, locale);
			log.info("message:{}", message);
			return new GenericResponse(message);
		} else {
			return new GenericResponse(messages.getMessage(
					"auth.message.invalid", null, locale));
		}
	}


	private String getAppUrl(HttpServletRequest request) {
		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		return appUrl;
	}

	private final SimpleMailMessage constructResetVerificationTokenEmail(final String contextPath, final Locale locale
			, final VerificationToken newToken, final User user) {
		final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
		log.info("Url: {}", confirmationUrl);
		final String message = messages.getMessage("message.resendToken", null, locale);
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Resend Registration Token");
		email.setText(message + " \r\n" + confirmationUrl);
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("support.email"));
		log.info("support.email:{}", env.getProperty("support.email"));
		return email;
	}

	private final SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
		final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
		log.info("url:{}", url);
		final String message = messages.getMessage("message.resetPassword", null, locale);
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Reset Password");
		email.setText(message + " \r\n" + url);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
}
