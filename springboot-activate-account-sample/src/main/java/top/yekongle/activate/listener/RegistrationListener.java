package top.yekongle.activate.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import top.yekongle.activate.entity.User;
import top.yekongle.activate.event.OnRegistrationCompleteEvent;
import top.yekongle.activate.service.UserService;;
/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年6月6日
*/
@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
    private UserService service;
  
    @Autowired
    private MessageSource messages;
  
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String emailFrom;
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		 this.confirmRegistration(event);
	}

	 private void confirmRegistration(OnRegistrationCompleteEvent event) {
	        User user = event.getUser();
	        String token = UUID.randomUUID().toString();
	        service.createVerificationToken(user, token);
	         
	        String recipientAddress = user.getEmail();
	        String subject = "Registration Confirmation";
	        String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
	        log.info("confirmationUrl: {}" + confirmationUrl);
	        String message = messages.getMessage("message.regSucc", null, event.getLocale());
	        log.info("recipientAddress: {}", user.getEmail());
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setFrom(emailFrom);
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message + "\r\n" + confirmationUrl);
	        mailSender.send(email);
	    }
}
