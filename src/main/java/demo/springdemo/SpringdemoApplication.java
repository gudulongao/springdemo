package demo.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringdemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringdemoApplication.class);
//        application.setBannerMode(Mode.OFF);
//        application.setBanner(new Banner() {
//            @Override
//            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//                out.print("阿西吧阿西吧阿西吧阿西吧阿西吧阿西吧\n");
//            }
//        });
        application.run();

    }

//    @Bean
//    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(buildHttpConnector());
//        return tomcat;
//    }
//
//    private Connector buildHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(80);
//        connector.setSecure(false);
//        connector.setRedirectPort(8080);
//        return connector;
//    }

}
