package com.example.membermanagementexample.api.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 스프링 컨테이너에서 페이지가 있는지 먼저 찾고(그것이 여기에 있는 home() 이다.
    // 만약에 컨테이너에 없다면 스태틱에서 찾게된다. (그러면 index.html 실행)
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
