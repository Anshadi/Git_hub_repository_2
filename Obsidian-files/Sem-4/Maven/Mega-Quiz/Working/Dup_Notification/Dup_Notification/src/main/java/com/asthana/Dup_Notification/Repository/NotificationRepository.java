package com.asthana.Dup_Notification.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.asthana.Dup_Notification.Notifications.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
