package com.example.pharmacy;

import com.example.pharmacy.controller.MeetingsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class PharmacyApplication  extends WebMvcConfigurationSupport {

    @Autowired
    private MeetingsController meetingsController;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(meetingsController);
    }

    public static void main(String[] args) {
        SpringApplication.run(PharmacyApplication.class, args);
    }

}
