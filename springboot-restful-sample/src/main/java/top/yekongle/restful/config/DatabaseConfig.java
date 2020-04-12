package top.yekongle.restful.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import lombok.extern.slf4j.Slf4j;
import top.yekongle.restful.entity.Employee;
import top.yekongle.restful.repository.EmployeeRepository;

/** 
* @Description: 数据库配置
* @Configuration: 表明这个是配置类
* @Sl4j: lombok注解，自动生成Logger
* @Author: Yekongle 
* @Date: Apr 7, 2020
*/

@Configuration
@Slf4j
public class DatabaseConfig {

	/**
	 * 应用上文下加载完后SpringBoot会执行所有注册到Spring中的CommandLineRunner
	 * */
	@Bean
	public CommandLineRunner initDatabase(EmployeeRepository repository) {
		// 执行CommandLineRunner的回调函数, 往数据库插入初始数据
		return args -> {
		     log.info("Preloading " + repository.save(new Employee("张三", "初级程序员")));
		     log.info("Preloading " + repository.save(new Employee("李四", "高级程序员")));
		};
	}
}
