package com.example.homework_5.controller

import com.example.homework_5.dto.GreetingDto
import com.example.homework_5.dto.IdDto
import com.example.homework_5.dto.NameDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class HomeworkController {

    // Добавьте в контроллер метод для эндпоинта get, принимающего в качестве аргумента параметр id
    // и возвращающего Json с полученным id
    @GetMapping
    fun get(@RequestBody idDto: IdDto): String  {
        return idDto.id
    }
    // Добавьте в контроллер метод для эндпоинта post, принимающего Json-запрос с полем name и возвращающего Json-ответ с полем greeting, содержащим приветствие для name.
    @PostMapping
    fun post(@RequestBody nameDto: NameDto): GreetingDto {
        return GreetingDto("Hello ${nameDto.name}")
    }
}