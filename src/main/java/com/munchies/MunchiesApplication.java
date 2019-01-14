package com.munchies;

import com.munchies.storage.StorageProperties;
import com.munchies.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


/**
 * <h1>Greetings Earthlings!</h1>
 * <h3> Welcome to Munchies - Food ordering App developed as a part of my internship at ING Software Nis, Serbia </>
 * <p>
 *     This App is offering a central repository of restaurants to employees of a certain company.
 *
 *     It facilitates group food ordering!
 * <p>
 * Employees are able to see a list of restaurants, restaurant details and make group orders.
 * <ul>It consists of two distinct parts :
 * <li>Administration portal
 * <li>and
 * <li>Employee portal  </ul>
 * <ul>Administration portal can be used (Only by authenticated/logged in users) for:
 * <li>-adding new restaurants;
 * <li>-editing existing restaurants;
 * <li>-seeing the list of users;
 * <li>-adding new users.
 * <ul/>
 * <br/>
 * <ul>Employee portal can be used (by anyone who accesses the app) for:
 * <li>-viewing restaurant information;
 * <li>-viewing restaurant details;
 * <li>-creating group orders;
 * <li>-adding individual orders to the group order.
 * <ul/>
 *
 * @author Miljana Milly Stamenkovic
 * @version 1.0
 * @since 2018-02-11
 */

@SpringBootApplication(scanBasePackages = {"com.munchies"})
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
public class MunchiesApplication extends AsyncConfigurerSupport {


    public static void main(String[] args) {
        SpringApplication.run(MunchiesApplication.class, args);
    }

    /**
     * init bean -> Initializing storage service by
     * creating an upload dir at the root location defined in application.properties
     * */
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }
}
