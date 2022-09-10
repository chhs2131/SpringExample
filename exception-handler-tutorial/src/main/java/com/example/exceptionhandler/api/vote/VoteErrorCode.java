package com.example.exceptionhandler.api.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum VoteErrorCode {
    UNAUTHORIZED_VOTE(UNAUTHORIZED, "투표할 권한이 없습니다"),
    ANOTHER_PAGE(MOVED_PERMANENTLY, "페이지가 이전되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
