package com.munchies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MunchiesApplication {


    public static void main(String[] args) {
        SpringApplication.run(MunchiesApplication.class, args);

    }


}
