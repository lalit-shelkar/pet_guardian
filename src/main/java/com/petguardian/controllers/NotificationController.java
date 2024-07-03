package com.petguardian.controllers;

import java.util.List;
import java.util.ArrayList;
import com.petguardian.Model.NotificationModelClass;

public class NotificationController {
    static List<NotificationModelClass> notification = new ArrayList<>();

    static void addNotification(NotificationModelClass obj) {
        notification.add(obj);
    }
}
