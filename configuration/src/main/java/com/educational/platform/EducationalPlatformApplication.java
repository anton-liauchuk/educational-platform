package com.educational.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@PropertySource("application-security.properties")
public class EducationalPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalPlatformApplication.class, args);
    }

}

