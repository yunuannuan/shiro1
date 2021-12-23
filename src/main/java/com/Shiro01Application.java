package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "com")
@ServletComponentScan("com")
public class Shiro01Application {
    public static void main(String[] args) {
        SpringApplication.run(Shiro01Application.class, args);
    }
}
