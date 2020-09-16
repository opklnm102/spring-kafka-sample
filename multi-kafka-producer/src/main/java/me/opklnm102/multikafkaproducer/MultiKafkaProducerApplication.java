package me.opklnm102.multikafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MultiKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiKafkaProducerApplication.class, args);
    }

}
