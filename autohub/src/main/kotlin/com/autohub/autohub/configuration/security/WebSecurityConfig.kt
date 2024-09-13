package com.autohub.autohub.configuration.security

import com.autohub.autohub.configuration.security.jwt.AuthEntryPoint
import com.autohub.autohub.configuration.security.jwt.AuthTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

val whiteListUrls: List<String> = listOf(
    "/api/v1/vehicles/**", "/api/v1/comments/**", "/api/v1/auth/**"
)

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
    private val authEntryPoint: AuthEntryPoint,
    private val authTokenFilter: AuthTokenFilter,
    private val authenticationProvider: AuthenticationProvider
) {

//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { csrf -> csrf.disable() }
//            .authorizeHttpRequests { auth ->
//                auth
//                    .requestMatchers(whiteListUrls.toString()).permitAll()
//                    .anyRequest().authenticated()
//            }
//            .sessionManagement { session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            }
//            .authenticationProvider(authenticationProvider)
//            .exceptionHandling{httpSecurityExceptionHandlingConfigurer ->
//                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authEntryPoint)
//            }
//            .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
//
//        return http.build()
//    }


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(*whiteListUrls.toTypedArray()).permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .exceptionHandling { httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authEntryPoint)
            }
            .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}