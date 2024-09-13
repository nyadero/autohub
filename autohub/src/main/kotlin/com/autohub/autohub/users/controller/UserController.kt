package com.autohub.autohub.users.controller

import com.autohub.autohub.auth.entity.User
import com.autohub.autohub.users.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    // all users
    @GetMapping("")
    fun allUsers(
       @RequestParam(defaultValue = "0") pageNumber: Int
    ): ResponseEntity<Page<User>> {
        return userService.allUsers(pageNumber)
    }

}