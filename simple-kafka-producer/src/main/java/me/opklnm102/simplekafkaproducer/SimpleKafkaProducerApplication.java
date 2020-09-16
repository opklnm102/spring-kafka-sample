package me.opklnm102.simplekafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleKafkaProducerApplication.class, args);
    }

}
