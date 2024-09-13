package com.autohub.autohub.auth.dto

data class RegisterDto(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
