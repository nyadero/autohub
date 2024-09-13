package com.autohub.autohub.auth.event

import com.autohub.autohub.auth.entity.User
import com.autohub.autohub.auth.enums.AuthEventType
import org.springframework.context.ApplicationEvent

data class AuthEvent(
    val user: User,
    val eventType: AuthEventType
): ApplicationEvent(
    user
)