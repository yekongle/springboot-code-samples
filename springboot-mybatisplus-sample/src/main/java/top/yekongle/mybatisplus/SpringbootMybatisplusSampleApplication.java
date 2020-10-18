package top.yekongle.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.yekongle.mybatisplus.mapper")
public class SpringbootMybatisplusSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplusSampleApplication.class, args);
    }

}
