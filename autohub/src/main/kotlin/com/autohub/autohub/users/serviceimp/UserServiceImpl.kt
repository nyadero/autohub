package com.autohub.autohub.users.serviceimp

import com.autohub.autohub.auth.entity.User
import com.autohub.autohub.users.repository.UserRepository
import com.autohub.autohub.users.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun allUsers(pageNumber: Int): ResponseEntity<Page<User>> {
        TODO("Not yet implemented")
    }
}