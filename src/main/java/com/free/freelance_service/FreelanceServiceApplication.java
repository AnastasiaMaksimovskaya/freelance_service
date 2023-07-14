package com.free.freelance_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class FreelanceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreelanceServiceApplication.class, args);
    }
}
