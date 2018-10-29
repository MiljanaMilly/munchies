package com.munchies;

import com.munchies.storage.StorageProperties;
import com.munchies.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.munchies"})
@EnableConfigurationProperties(StorageProperties.class)
public class MunchiesApplication {



    public static void main(String[] args) {
        SpringApplication.run(MunchiesApplication.class, args);
    }


}
