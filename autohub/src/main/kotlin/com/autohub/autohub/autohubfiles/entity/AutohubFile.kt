package com.autohub.autohub.autohubfiles.entity

import com.autohub.autohub.vehicles.entity.Vehicle
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "files")
data class AutohubFile(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String,
    var fileurl: String,
    var filename: String,
    var filesize: Long,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference(value = "vehicle-files")
    var vehicle: Vehicle
)
