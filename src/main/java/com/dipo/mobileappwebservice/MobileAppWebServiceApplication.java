package com.dipo.mobileappwebservice;

import com.dipo.mobileappwebservice.Security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
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


    @Bean(name = "AppProperties")
    public AppProperties appProperties(){
        return new AppProperties();
    }
}
