package com.educational.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EducationalPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalPlatformApplication.class, args);
    }

}

