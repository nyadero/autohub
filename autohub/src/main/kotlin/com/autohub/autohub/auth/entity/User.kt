package com.autohub.autohub.auth.entity

import com.autohub.autohub.auth.enums.Role
import com.autohub.autohub.vehicles.entity.Vehicle
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val name: String,
    @Column(unique = true)
    val email: String,
    val userName: String,
    @JsonIgnore
    val uPassword: String,

    val enabled:  Boolean = false,
    val role: Role = Role.USER,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    val vehicle: List<Vehicle>? = null,

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    val verificationToken: VerificationToken? = null,

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    val passwordResetToken: PasswordResetToken? = null,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
): UserDetails {

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    @JsonIgnore
    override fun isEnabled(): Boolean {
        return enabled
    }

    @JsonIgnore
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
      return listOf(GrantedAuthority{role.name})
    }

    @JsonIgnore
    override fun getPassword(): String? {
        return uPassword
    }

    override fun getUsername(): String {
        return email
    }

}