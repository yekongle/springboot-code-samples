package top.yekongle.jasypt.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YekongleEncryptorCfg {

/*    @Bean(name = "yekongleEncryptorBean")
    public StringEncryptor yekongleStringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("yekongle");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        //config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        //config.setIvGeneratorClassName("org.jasypt.salt.RandomIVGenerator");
       config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor;
    }*/
}
