package com.example.exceptionhandler.api.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum BoardErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    ARTICLE_NOT_FOUND(NOT_FOUND, "해당 게시글이 없습니다"),
    CANT_CREATE_ARTICLE(ACCEPTED, "요청을 정상적으로 전달하였으나, 글을 작성하지 못했습니다"),
    SUCCESS_DELETE(NO_CONTENT, "정상적으로 글을 삭제하였습니다")
    ;

    private final HttpStatus status;
    private final String message;
}
