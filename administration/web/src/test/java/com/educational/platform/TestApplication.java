package com.educational.platform;

import org.axonframework.springboot.autoconfig.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TestApplication {
}
