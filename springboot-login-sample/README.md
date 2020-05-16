### Description
Login sample.

### Run
```
mvn springboot:run -Pdev
```

### Compile
```bash
mvn clean compile -P [env] -DskipTests
``` 

### Package
```
mvn clean package -P [env] -DskipTests
```

### Generate mybatis xml and class
```
mvn mybatis-generator:generate
```