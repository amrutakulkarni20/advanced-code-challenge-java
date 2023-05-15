package com.statista.code.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class App {
    public static void main(String[] args) {
        System.out.println("Hello!!! Booking Application started. ");
        SpringApplication.run(App.class, args);
    }
}