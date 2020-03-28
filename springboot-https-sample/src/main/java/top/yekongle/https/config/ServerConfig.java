package top.yekongle.https.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * @Description: 自定义内置Tomcat, 使访问 http 时可以自动跳转到 https
 * @Author: Yekongle
 * </pre>
 */

@Configuration
public class ServerConfig {

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);

                // 配置静态资源访问
             /*SecurityConstraint securityConstraint1 = new SecurityConstraint();
                securityConstraint1.setUserConstraint("NONE");
                SecurityCollection collection1 = new SecurityCollection();
                collection.addPattern("/static/");
                securityConstraint.addCollection(collection1);
                context.addConstraint(securityConstraint1);*/
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        // 设置http端口80,访问时不用加端口号
        connector.setPort(80);
        connector.setSecure(false);
        // 跳转到https端口
        connector.setRedirectPort(443);
        return connector;
    }
}
