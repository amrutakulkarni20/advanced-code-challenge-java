package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingModel;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendEmailNotification(BookingModel bookingModel);
}
