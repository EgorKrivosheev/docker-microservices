package by.grodno.krivosheev.transformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TransformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransformationApplication.class, args);
    }
}


