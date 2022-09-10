package com.example.exceptionhandler.api.board;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/board")
public class BoardController {
    @GetMapping(value = "/{number}")
    public String boardGetException(@PathVariable String number) {
        //throw new CustomException(ARTICLE_NOT_FOUND);
        return null;
    }

    @PostMapping(value = "/{number}")
    public String boardPostException(@PathVariable String number) {
        //throw new CustomException(CANT_CREATE_ARTICLE);
        return null;
    }

    @DeleteMapping(value = "/{number}")
    public String boardDeleteException(@PathVariable String number) {
        //throw new CustomException(SUCCESS_DELETE);
        return null;
    }
}
