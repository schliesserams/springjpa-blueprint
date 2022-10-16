package com.ams.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SpringJpaBlueprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaBlueprintApplication.class, args);
    }

}
