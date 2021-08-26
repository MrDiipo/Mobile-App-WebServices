package com.dipo.mobileappwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MobileAppWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileAppWebServiceApplication.class, args);
    }

}
