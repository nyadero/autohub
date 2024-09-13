package com.autohub.autohub.configuration.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.util.HashMap

@Component
class AuthEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val body: MutableMap<String, String> = HashMap()
        body["status"] = HttpServletResponse.SC_UNAUTHORIZED.toString()
        body["error"] = "Unauthorized to access this resource"
        body["message"] = authException.message.toString()
        body["path"] = request.servletPath
        val mapper = ObjectMapper()
        mapper.writeValue(response.outputStream, body)
    }
}
