package com.bsep.smart.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan("com.bsep.smart.home.configProperties")
@EnableMongoRepositories(basePackages = "com.bsep.smart.home.mongorepository")
@EnableJpaRepositories(basePackages = "com.bsep.smart.home.repository")
@EnableScheduling
public class SmartHomeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SmartHomeApplication.class, args);
    }
}
