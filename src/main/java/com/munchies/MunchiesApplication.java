package com.munchies;

import com.munchies.model.Role;
import com.munchies.model.User;
import com.munchies.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.munchies"})
public class MunchiesApplication {



    public static void main(String[] args) {
        SpringApplication.run(MunchiesApplication.class, args);

    }


}
