package com.autohub.autohub.comments.entity

import com.autohub.autohub.vehicles.entity.Vehicle
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity(name = "comments")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val comment: String,
//    val replies: List<Comment>,
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value = "vehicle-comments")
    val vehicle: Vehicle,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
)
