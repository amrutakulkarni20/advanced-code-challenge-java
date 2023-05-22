package com.statista.code.challenge.service;

import com.statista.code.challenge.model.Notification;

public interface NotificationService {
    void sendNotification(Notification notification);
}
