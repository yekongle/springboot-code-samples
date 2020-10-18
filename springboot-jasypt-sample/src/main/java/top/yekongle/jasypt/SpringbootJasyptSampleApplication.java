package top.yekongle.jasypt;

import io.lettuce.core.dynamic.annotation.Command;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class SpringbootJasyptSampleApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootJasyptSampleApplication.class);

    @Autowired
    private ApplicationContext appCtx;

    @Autowired
    private StringEncryptor yekongleEncryptorBean;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJasyptSampleApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Environment environment = appCtx.getBean(Environment.class);

        // 获取配置文件的敏感信息
        String mysqlPwd = environment.getProperty("spring.datasource.password");
        String redisPwd = environment.getProperty("spring.redis.password");

        // 加密
        String mysqlEncryptedPwd = encrypt(mysqlPwd);
        String redisEncryptedPwd = encrypt(redisPwd);

        LOGGER.info("MySQL 原始明文密码：{}", mysqlPwd);
        LOGGER.info("Redis 原始明文密码：{}", redisPwd);

//        LOGGER.info("MySQL 原始明文密码加密结果：{}", mysqlEncryptedPwd);
//        LOGGER.info("Redis 原始明文密码加密结果：{}", redisEncryptedPwd);
    }

    private String encrypt(String originPwd) {
        String encryptStr = yekongleEncryptorBean.encrypt(originPwd);
        return encryptStr;
    }

    private String decrypt(String encryptedPwd) {
        String decryptStr = yekongleEncryptorBean.decrypt(encryptedPwd);
        return decryptStr;
    }
}
