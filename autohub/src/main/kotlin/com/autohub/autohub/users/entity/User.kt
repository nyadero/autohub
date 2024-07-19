package com.autohub.autohub.users.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: String,

    private val name: String,

    @Column(unique = true)
    private val email: String,

    @Column(unique = true)
    private val userName: String,

    private val password: String,

    private val isEnabled: Boolean,

)