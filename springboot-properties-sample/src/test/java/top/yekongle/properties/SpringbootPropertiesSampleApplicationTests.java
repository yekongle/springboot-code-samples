package top.yekongle.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yekongle.properties.config.SongConfig;

@SpringBootTest
class SpringbootPropertiesSampleApplicationTests {

	@Autowired
	private SongConfig songConfig;

	@Test
	void contextLoads() {
	}

}
