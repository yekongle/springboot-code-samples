package top.yekongle.activate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

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
        registry.addViewController("login.html");
        registry.addViewController("/registration.html");
        registry.addViewController("/successRegister.html");
        registry.addViewController("/home.html");
        registry.addViewController("/logout.html");
        registry.addViewController("/invalidSession.html");
        registry.addViewController("/badUser.html");
        registry.addViewController("/emailError.html");
        registry.addViewController("/forgetPassword.html");
        registry.addViewController("/changePassword.html");
        registry.addViewController("/updatePassword.html");
    }

    /*
     * 配置拦截器获取URL中的 lang 参数 (?lang=zh_CN）
     * */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /*
     * 注册我们自定义的区域解析器，
     * 一旦将区域解析器注册到Spring容器中
     * 则SpingBoot默认提供的区域解析器将不会自动注册
     */
    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINESE);
        return cookieLocaleResolver;
    }
}
