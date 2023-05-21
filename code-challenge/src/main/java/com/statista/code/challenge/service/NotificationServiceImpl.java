package com.statista.code.challenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.statista.code.challenge.model.BookingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.email}")
    private String senderEmail;
    @Value("${spring.mail.subject}")
    private String subject;

    Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    @Override
    public void sendEmailNotification(BookingModel bookingModel) {
        String body = createEmailBody(bookingModel);
        logger.info("Host: {} ",  host);
        logger.info("Email From: {} ", senderEmail);
        logger.info("Email To: {} ", bookingModel.getEmail());
        logger.info("Subject: {} ", subject);
        logger.info("Body:\n{} ", body);
    }

    private String createEmailBody(BookingModel bookingModel) {
        return "We are pleased to confirm your Booking. Please review the details below:\n\n"
                + "Booking Details:\n\n"
                + "Booking Id: " + bookingModel.getBookingId() + "\n"
                + "Department: " + bookingModel.getDepartment() + "\n"
                + "Start date of subscription: " + bookingModel.getSubscriptionStartDate();
    }
}
