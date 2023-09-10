package com.example.kotlinhelloworld

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessageController(val messageService:MessageService) {
    @GetMapping("")
    fun getList():List<Message> {
        return messageService.findMessages()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): List<Message> {
        return messageService.findMessageById(id)
    }

    @PostMapping("")
    fun post(@RequestBody message:Message) {
        messageService.save(message)
    }
}