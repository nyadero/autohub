package com.autohub.autohub.configuration.responses

data class ApiResponse<T>(
    val data: Data<T>?,
    val message: String,
    val type: ResponseType
)