package inno.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"inno.tech"})
@EntityScan(basePackages = {"inno.tech"})
public class ApplicationTest {

    static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
