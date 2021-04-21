package ru.itis.sharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("ru.itis.sharing.model")
@SpringBootApplication
public class HeySharingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeySharingBackendApplication.class, args);
    }

}
