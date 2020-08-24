package top.yekongle.activate.event;

import java.util.Locale;

import lombok.*;
import org.springframework.context.ApplicationEvent;

import top.yekongle.activate.entity.User;

/** 
* @Description: 
* @Author: Yekongle 
* @Date: 2020年6月6日
*/
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private String appUrl;
	private Locale locale;
	private User user;

	public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
		super(user);
 		this.user = user;
 		this.locale = locale;
		this.appUrl = appUrl;
	}
}
