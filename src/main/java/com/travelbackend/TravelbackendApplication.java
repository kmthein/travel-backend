package com.travelbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelbackendApplication.class, args);
    }

}
