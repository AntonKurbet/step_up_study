package stepup.study;

import stepup.study.services.impl.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StepUpStudy4Application implements CommandLineRunner {

    @Value("${application.dataPath}")
    private String dataPath;
    @Autowired
    private MainService mainService;

    public static void main(String[] args) {
        SpringApplication.run(StepUpStudy4Application.class, args);
    }

    @Override
    public void run(String... args) {
        mainService.run(dataPath);
    }

}
