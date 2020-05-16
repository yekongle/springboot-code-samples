package top.yekongle.login.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
        log.info("onAuthenticationFailure");
    	setDefaultFailureUrl("/login?error=true");

        super.onAuthenticationFailure(request, response, exception);
		 
        request.getSession()
            .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception.getMessage());
    }
}