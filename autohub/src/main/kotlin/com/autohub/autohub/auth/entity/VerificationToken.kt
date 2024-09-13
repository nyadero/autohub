package com.autohub.autohub.auth.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date


@Entity(name = "verification_tokens")
data class VerificationToken(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    val token: String,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    val user: User,

    val expiresIn: Date? = null,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,

) {

       companion object{
           const val EXPIRATION_TIME: Int = 2
       }

        fun calculateExpirationDate(): Date {
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(Date().getTime())
            calendar.add(Calendar.HOUR, VerificationToken.EXPIRATION_TIME)
            return Date(calendar.getTime().getTime())
        }

}