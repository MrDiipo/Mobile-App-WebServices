package com.dipo.mobileappwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MobileAppWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileAppWebServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder biCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext applicationContext(){
        return new SpringApplicationContext();
    }

}
