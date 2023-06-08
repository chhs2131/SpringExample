package com.fcmtutorial.fcmtutorial.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final FirebaseApp firebaseApp;

    public String pushNotification(String clientToken, String title, String msg) throws FirebaseMessagingException {
        // See documentation on defining a message payload.
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(msg)
                        .build()
                )
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(clientToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance().send(message);

        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
        return response;
    }
}
