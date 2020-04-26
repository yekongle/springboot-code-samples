package top.yekongle.pagehelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 * @MapperScan：指定mapper所在包
 * */
@SpringBootApplication
@MapperScan("top.yekongle.pagehelper.mapper")
public class SpringbootPagehelperSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPagehelperSampleApplication.class, args);
	}

}
