package com.statista.code.challenge.service;

import com.statista.code.challenge.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void sendNotification(Notification notification) {
        logger.info("Host: {} ",  notification.getHost());
        logger.info("Email From: {} ", notification.getEmailFrom());
        logger.info("Email To: {} ", notification.getEmailTo());
        logger.info("Subject: {} ", notification.getSubject());
        logger.info("Body:\n{} ", notification.getBody());
    }

}
