package com.autohub.autohub.auth.repository

import com.autohub.autohub.auth.entity.PasswordResetToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PasswordResetTokenRepository: JpaRepository<PasswordResetToken, String> {
}