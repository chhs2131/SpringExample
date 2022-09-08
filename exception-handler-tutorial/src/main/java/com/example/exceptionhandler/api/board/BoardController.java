package com.example.exceptionhandler.api.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board")
public class BoardController {
    @GetMapping(value = "")
    public String boardListException() {
        throw new BoardE
    }
}
