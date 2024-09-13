package com.autohub.autohub.auth.serviceimp

import com.autohub.autohub.auth.dto.RegisterDto
import com.autohub.autohub.auth.dto.SigninDto
import com.autohub.autohub.auth.entity.User
import com.autohub.autohub.auth.entity.VerificationToken
import com.autohub.autohub.auth.enums.Role
import com.autohub.autohub.auth.repository.AuthRepository
import com.autohub.autohub.auth.repository.VerificationTokenRepository
import com.autohub.autohub.auth.response.LoginResponse
import com.autohub.autohub.auth.service.AuthService
import com.autohub.autohub.configuration.exceptions.EntityNotFoundException
import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.configuration.security.jwt.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
   private  val authRepository: AuthRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtils: JwtUtils,
    private val verificationTokenRepository: VerificationTokenRepository
) : AuthService {

    override fun registerUser(dto: RegisterDto): User {
        // check if user already exists
        val exists: User? = authRepository.findByEmail(dto.email)
        if (exists != null){
            throw IllegalArgumentException("User exists ")
        }
        // compare passwords
        if (dto.password != dto.confirmPassword){
            throw IllegalArgumentException("Passwords do not match")
        }
        // hash password
        // save user to db
        val user: User = User(id = null, name = dto.name, email = dto.email, uPassword = passwordEncoder.encode(dto.password), userName = dto.email, role = Role.USER)
        // publish registration complete event

        return authRepository.save(user)
    }

    override fun verifyEmail(token: String) : Unit {
//        TODO("Not yet implemented")
        val verificationToken: VerificationToken? = verificationTokenRepository.findByToken(token)
    }

    override fun loginUser(signinDto: SigninDto): LoginResponse {
       val usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken =
           UsernamePasswordAuthenticationToken(signinDto.email, signinDto.password)
        val user: User? = authRepository.findByEmail(signinDto.email);
        print(usernamePasswordAuthenticationToken)
        val token = user?.let { jwtUtils.generateJwtToken(it) }
        return LoginResponse(usernamePasswordAuthenticationToken.name, token.toString())
    }

}