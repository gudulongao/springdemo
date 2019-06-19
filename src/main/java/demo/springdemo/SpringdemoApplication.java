package demo.springdemo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class SpringdemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringdemoApplication.class);
//        application.setBannerMode(Mode.OFF);
        application.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                out.print("阿西吧阿西吧阿西吧阿西吧阿西吧阿西吧\n");
            }
        });
        application.run();

    }

}
