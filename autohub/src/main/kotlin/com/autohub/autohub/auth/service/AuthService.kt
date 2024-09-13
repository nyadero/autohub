package com.autohub.autohub.auth.service

import com.autohub.autohub.auth.dto.RegisterDto
import com.autohub.autohub.auth.dto.SigninDto
import com.autohub.autohub.auth.entity.User
import com.autohub.autohub.auth.response.LoginResponse
import com.autohub.autohub.configuration.responses.ApiResponse
import org.springframework.http.ResponseEntity

interface AuthService {
    fun registerUser(dto: RegisterDto): User
    fun verifyEmail(email: String): Unit
    fun loginUser(signinDto: SigninDto): LoginResponse
}