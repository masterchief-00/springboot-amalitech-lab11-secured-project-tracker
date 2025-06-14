package com.kwizera.springbootlab11securedprojecttracker;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootLab11SecuredProjectTrackerApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USER"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGODB_URI"));


        SpringApplication.run(SpringbootLab11SecuredProjectTrackerApplication.class, args);
    }

}
