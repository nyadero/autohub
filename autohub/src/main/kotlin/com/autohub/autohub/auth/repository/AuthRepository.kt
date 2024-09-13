package com.autohub.autohub.auth.repository

import com.autohub.autohub.auth.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepository : JpaRepository<User, String>{
    fun findByEmail(email: String):User?
    fun findByUserName(email: String): User
}