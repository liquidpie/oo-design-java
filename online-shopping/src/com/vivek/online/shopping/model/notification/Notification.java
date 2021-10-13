package com.vivek.online.shopping.model.notification;

import com.vivek.online.shopping.model.user.Account;

import java.time.LocalDate;

public abstract class Notification {

    private String notificationId;
    private LocalDate createdOnn;
    private String content;

    abstract void sendNotification(Account account);

}
