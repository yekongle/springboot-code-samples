package top.yekongle.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 
* @Description: I18n config
* @Author: Yekongle 
* @Date: Mar 22, 2020
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置对"/"的请求映射到login
        //如果没有逻辑业务，没有必要用控制器方法对请求进行映射
        registry.addViewController("/").setViewName("login");
    }
 
 
    /**
     * 注册我们自定义的区域解析器，
     * 一旦将区域解析器注册到Spring容器中
     * 则SpingBoot默认提供的区域解析器 AcceptHeaderLocaleResolver 将不会自动注册
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }
 
}
