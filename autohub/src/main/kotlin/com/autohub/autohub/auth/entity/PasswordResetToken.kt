package com.autohub.autohub.auth.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.Date

@Entity(name = "password_reset_tokens")
data class PasswordResetToken(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    val token: String,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    val user: User,

    val expiresIn: Date,

    @CreationTimestamp
    val createdAt: LocalDateTime,

    @UpdateTimestamp
    val updatedAt: LocalDateTime
){

}
