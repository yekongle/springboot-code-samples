package top.yekongle.springbootadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringbootAdminSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdminSampleApplication.class, args);
    }

}
