package com.autohub.autohub.users.service

import com.autohub.autohub.auth.entity.User
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

interface UserService {
    fun allUsers(pageNumber: Int): ResponseEntity<Page<User>>
}