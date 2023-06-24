package com.statista.code.challenge;

import com.statista.code.challenge.service.NotificationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class BookingApplication {

    private static Logger logger = LoggerFactory.getLogger(BookingApplication.class);
    public static void main(String[] args) {
        logger.info("Welcome to Booking Service");
        SpringApplication.run(BookingApplication.class, args);
    }
}