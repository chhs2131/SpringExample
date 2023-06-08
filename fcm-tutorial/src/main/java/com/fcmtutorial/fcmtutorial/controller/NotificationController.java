package com.fcmtutorial.fcmtutorial.controller;

import com.fcmtutorial.fcmtutorial.service.NotificationService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final FirebaseApp firebaseApp;

    @GetMapping("/inform")
    public String getFirebaseAppName() {
        return firebaseApp.toString();
    }

    /**
     * FCM을 통해 push 알림을 보냅니다.
     * @param clientToken clientDevice의 고유한 token 번호
     * @param title push알림 제목
     * @param msg push알림 본문
     * @return proejcts명과 메세지 번호 String을 반환합니다.
     * @throws FirebaseMessagingException
     */
    @GetMapping("/send")
    public String sendMessage(
            @RequestParam String clientToken,
            @RequestParam(required = false, defaultValue = "test title") String title,
            @RequestParam(required = false, defaultValue = "test msg body") String msg
    ) throws FirebaseMessagingException {
        // final String registrationToken = "fu9k1Z5iQGyOEKrdiQtkEx:APA91bEsZfUFD_hnsubfww5HTakQzViLh4FVBlfonjkKPGeoHQ4WcOp_aXJQ6UDprvxYH_nAjrmgia3yj02H5i-_4BS15RToxt8z6nxVOUQyXZri1mcg9FzB8WTaFCuCX814GwGHOISm";

        return notificationService.pushNotification(clientToken, title, msg);
    }
}
