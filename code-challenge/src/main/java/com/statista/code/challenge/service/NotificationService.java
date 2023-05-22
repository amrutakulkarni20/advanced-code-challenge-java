package com.statista.code.challenge.service;

import com.statista.code.challenge.model.Notification;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendNotification(Notification notification);
}
