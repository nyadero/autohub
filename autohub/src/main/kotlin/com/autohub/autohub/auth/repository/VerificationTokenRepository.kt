package com.autohub.autohub.auth.repository

import com.autohub.autohub.auth.entity.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VerificationTokenRepository : JpaRepository<VerificationToken, String> {
    fun findByToken(token: String): VerificationToken?
}