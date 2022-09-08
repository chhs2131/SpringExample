package com.example.exceptionhandler.api.vote;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {
    @GetMapping(value = "/{number}")
    public String voteGetException(@PathVariable String number) {
        //throw new CustomException(MOVED_PERMANENTLY);
        return null;
    }

    @PostMapping(value = "/{number}")
    public String votePostException(@PathVariable String number) {
        //throw new CustomException(MOVED_PERMANENTLY);
        return null;
    }
}
