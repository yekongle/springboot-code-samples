package top.yekongle.registration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 
* @Description: web mvc 配置
* @Author: Yekongle 
* @Date: 2020年5月8日
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	// 如果没有逻辑业务，则没有必要用控制器方法对请求进行映射
    	registry.addViewController("/registration.html");
    	registry.addViewController("/successRegister.html");
    }
}
