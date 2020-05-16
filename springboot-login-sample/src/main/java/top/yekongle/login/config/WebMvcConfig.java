package top.yekongle.login.config;

import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/** 
* @Description: web mvc 配置
* @Author: Yekongle 
* @Date: 2020年5月8日
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
         * 设置对"/"的请求映射到login, 如果没有逻辑业务，
         * 则没有必要用控制器方法对请求进行映射
         * */
        registry.addViewController("/").setViewName("forward:/login");
        registry.addViewController("/registration.html");
        registry.addViewController("/successRegister.html");
        registry.addViewController("/home.html");
        registry.addViewController("/logout.html");
        registry.addViewController("/invalidSession.html");
    }
}
