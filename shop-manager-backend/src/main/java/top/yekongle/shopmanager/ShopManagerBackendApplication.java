package top.yekongle.shopmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("top.yekongle.shopmanager.mapper")
public class ShopManagerBackendApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ShopManagerBackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopManagerBackendApplication.class, args);
	}

}