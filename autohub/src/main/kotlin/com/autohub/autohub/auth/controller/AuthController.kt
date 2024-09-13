package com.autohub.autohub.auth.controller

import com.autohub.autohub.auth.dto.RegisterDto
import com.autohub.autohub.auth.dto.SigninDto
import com.autohub.autohub.auth.entity.User
import com.autohub.autohub.auth.response.LoginResponse
import com.autohub.autohub.auth.service.AuthService
import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.configuration.responses.Data
import com.autohub.autohub.configuration.responses.ResponseType
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    // register
    @PostMapping("/register")
   fun registerUser(
       @RequestBody registerDto:RegisterDto
   ): ResponseEntity<ApiResponse<User>>{
      val user: User = authService.registerUser(registerDto)
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(Data(user), "Registered successful",
            ResponseType.Success
        ))
   }

    // verify email
    @GetMapping("/verify-email")
    fun verifyEmail(
        @PathVariable("token") token: String
    ): ResponseEntity<ApiResponse<Unit>>{
        authService.verifyEmail(token)
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(null, "Email verified successfully",
            ResponseType.Success
        ))
    }

    // sign in
    @PostMapping("/login")
    fun loginUser(
        @RequestBody signinDto: SigninDto
    ): ResponseEntity<ApiResponse<LoginResponse>>{
        val loginResponse: LoginResponse = authService.loginUser(signinDto)
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(Data(loginResponse), "Login successful",
            ResponseType.Success
        ))
    }
}