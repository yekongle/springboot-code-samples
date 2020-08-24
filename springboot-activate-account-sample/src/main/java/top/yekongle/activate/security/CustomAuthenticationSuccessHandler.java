package top.yekongle.activate.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
* @Author: Yekongle 
* @Date: 2020年5月13日
*/
@Slf4j
@Component("authenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("onAuthenticationSuccess");
		redirectStrategy.sendRedirect(request, response, "/home.html");

		// 获取session,如果 session不存在，则返回null。
		final HttpSession session = request.getSession(false);
		if (session != null) {
			// session 有效期 30 min
			session.setMaxInactiveInterval(30*60);
			String username = this.getCurrentUsername(authentication);
			session.setAttribute("user", username);
		}
		// 清除 session 中的 AUTHENTICATION_EXCEPTION 属性
		clearAuthenticationAttributes(request);
	}	
	
	
	private String getCurrentUsername(Authentication authentication) {
		String username = null;
		if (authentication.getPrincipal() instanceof UserDetails) {
			username = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			username = authentication.getName();
		}
		return username; 
	}

    protected void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
	
}
