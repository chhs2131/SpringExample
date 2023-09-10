package com.example.kotlinhelloworld

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class HelloController {
    @GetMapping("/hello")
    fun hello(@RequestParam("name", required = false, defaultValue = "World") name: String) = "Hello, $name!"

    @GetMapping("/")
    fun getList():List<Message> {
        return listOf(
            Message("2", "hello"),
            Message("2", "good bye"),
            Message("3", "sure"),
        );
    }
}
