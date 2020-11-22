package top.yekongle.shopmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yekongle
 * @date 2020/11/16 23:45
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
         * 设置对"/"的请求映射到login, 如果没有逻辑业务，
         * 则没有必要用控制器方法对请求进行映射
         * */
        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index.");
    }

}
