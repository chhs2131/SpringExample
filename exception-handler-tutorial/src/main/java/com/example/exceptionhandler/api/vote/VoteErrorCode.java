package com.example.exceptionhandler.api.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum VoteErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    UNAUTHORIZED_VOTE(UNAUTHORIZED, "투표할 권한이 없습니다"),
    NOT_TEAPORT(I_AM_A_TEAPOT, "i'm a teaport"),
    ANOTHER_PAGE(MOVED_PERMANENTLY, "페이지가 이전되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
