package com.bsep.smart.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan("com.bsep.smart.home.configProperties")
public class SmartHomeApplication {

    public static void main(final String[] args) { SpringApplication.run(SmartHomeApplication.class, args);}
}
