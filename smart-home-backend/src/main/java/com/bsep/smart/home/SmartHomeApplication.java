package com.bsep.smart.home;

import com.bsep.smart.home.services.LoadKieSession;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan("com.bsep.smart.home.configProperties")
@EnableMongoRepositories(basePackages = "com.bsep.smart.home.mongorepository")
@EnableJpaRepositories(basePackages = "com.bsep.smart.home.repository")
@EnableScheduling
@RequiredArgsConstructor
public class SmartHomeApplication {

    private final LoadKieSession loadKieSession;


    public static void main(final String[] args) {
        SpringApplication.run(SmartHomeApplication.class, args);
    }

    @Bean
    public KieSession kieSession() throws IOException {
        return loadKieSession.execute();
    }
}
