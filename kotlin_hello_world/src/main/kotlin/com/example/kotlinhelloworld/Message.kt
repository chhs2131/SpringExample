package com.example.kotlinhelloworld
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("MESSAGES")
data class Message(@Id var id: String?, val text: String)
