package com.fcmtutorial.fcmtutorial.dto;

import lombok.Data;

import java.util.Map;

@Data
@Deprecated
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;
}
