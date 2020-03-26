package top.yekongle.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Component 将其注册成 Bean
 * @PropertySource 指定类路径下配置文件
 * @ConfigurationProperties 自动生 getter setter 方法
 * */
@Component
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "person")
@Data
public class PersonConfig {
	private String name;
	private int age;
}
