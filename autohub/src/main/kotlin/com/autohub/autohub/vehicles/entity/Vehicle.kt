package com.autohub.autohub.vehicles.entity

import com.autohub.autohub.autohubfiles.entity.AutohubFile
import com.autohub.autohub.vehicles.enums.DriveTrain
import com.autohub.autohub.vehicles.enums.EngineAspiration
import com.autohub.autohub.vehicles.enums.EngineLayout
import com.autohub.autohub.vehicles.enums.FuelType
import com.autohub.autohub.vehicles.enums.TransmissionType
import com.autohub.autohub.vehicles.enums.VehicleMake
import com.autohub.autohub.vehicles.enums.VehicleModel
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity(name = "vehicles")
data class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    var name: String,

    @Enumerated(value = EnumType.STRING)
    var make: VehicleMake,

    @Enumerated(value = EnumType.STRING)
    var model: VehicleModel,

    var price: Double,

    var mileage: Int,

    var location: String,

    var description: String,

    @Enumerated(value = EnumType.STRING)
    var fuelType: FuelType,

    @Enumerated(value = EnumType.STRING)
    var driveTrain: DriveTrain,

    @Enumerated(value = EnumType.STRING)
    var transmissionType: TransmissionType,

    @Enumerated(value = EnumType.STRING)
    var engineAspiration: EngineAspiration,

    @Enumerated(value = EnumType.STRING)
    var engineLayout: EngineLayout,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicle")
    @JsonManagedReference(value = "vehicle-files")
    var files: List<AutohubFile>? = null,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null


)
