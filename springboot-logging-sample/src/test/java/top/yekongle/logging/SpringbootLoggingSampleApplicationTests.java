package top.yekongle.logging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLoggingSampleApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(SpringbootLoggingSampleApplicationTests.class);

	@Test
	void contextLoads() {
		logger.debug("debug日志");

		logger.info("info日志");

		logger.warn("warn日志");

		logger.error("error日志");
	}

}
