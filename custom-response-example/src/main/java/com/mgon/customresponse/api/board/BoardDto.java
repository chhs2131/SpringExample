package com.mgon.customresponse.api.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {
    private String id;
    private String title;
    private String content;
}
