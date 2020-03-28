### Description
HTTPS practice.

### Run
``` bash
mvn spring-boot:run -Pdev
```

### Compile
```bash
mvn clean compile -P [env] -DskipTests
``` 

### Package
```
mvn clean package -P [env] -DskipTests
```

### JDK keytool
``` bash
keytool -genkey -alias yekong -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore yekong.p12 -validity 3650
```

#### 参数解释
- genkey: 生成SSL证书
- alias: 证书别名
- storetype: 秘钥仓库类型
- keyalg: 生成证书算法
- keysize: 证书大小
- keystore: 生成证书保存路径，也是证书名称
- validity: 证书有效期

