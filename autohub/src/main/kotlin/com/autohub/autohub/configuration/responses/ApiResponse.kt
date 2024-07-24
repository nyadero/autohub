package com.autohub.autohub.configuration.responses

data class ApiResponse(
    val data: Object,
    val message: String,
    val type: ResponseType
)