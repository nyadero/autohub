package com.autohub.autohub.configuration.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthTokenFilter(
     val jwtUtils: JwtUtils,
     val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt: String? = parseJwt(request)
            println("Extracted JWT: $jwt")
            if (jwt != null) {
                val username: String = jwtUtils.extractUsername(jwt)
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                val isTokenValid: Boolean = jwtUtils.isTokenValid(jwt, userDetails)
                println("Is token valid: $isTokenValid")
                if (isTokenValid) {
                    val authenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authenticationToken
                } else {
                    println("Invalid token")
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    response.writer.write("Invalid or expired token")
                    return
                }
            }
        } catch (ex: Exception) {
            println("Error authenticating token")
            logger.error("Token not authenticated: ${ex.message}")
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("Error authenticating token")
            return
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth: String? = request.getHeader("Authorization")
        println("Authorization Header: $headerAuth")
        if (StringUtils.hasText(headerAuth) && headerAuth!!.startsWith("Bearer ")) {
            return headerAuth.substring(7) // Extract token after "Bearer "
        }
        return null
    }


}