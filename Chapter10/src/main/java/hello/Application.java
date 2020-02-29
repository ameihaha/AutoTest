package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.course") //com...该包需要被扫描
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
