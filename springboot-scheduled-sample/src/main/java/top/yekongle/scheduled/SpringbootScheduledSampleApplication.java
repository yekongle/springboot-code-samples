package top.yekongle.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnableScheduling 开启任务调度
 * */
@SpringBootApplication
@EnableScheduling
public class SpringbootScheduledSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootScheduledSampleApplication.class, args);
	}

}
