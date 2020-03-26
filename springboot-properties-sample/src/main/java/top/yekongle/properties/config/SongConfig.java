package top.yekongle.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Component 将其注册成 Bean
 * @ConfigurationProperties 指定属性前缀，绑定到类属性中
 * @Data 自动生 getter setter 方法
 * */
@Component
@ConfigurationProperties(prefix = "song")
@Data
public class SongConfig {
	private String author;
	private String name;
}
