package com.fcmtutorial.fcmtutorial.controller;

import com.fcmtutorial.fcmtutorial.dto.Note;
import com.fcmtutorial.fcmtutorial.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note, @RequestParam String topic) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, topic);
    }
}
g