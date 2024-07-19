package com.autohub.autohub.vehicles.dto

import org.springframework.web.multipart.MultipartFile


data class VehicleDto(
    val name: String,
    val make: String,
    val model: String,
    val mileage: Int,
    val price: Double,
    val description: String,
    val location: String,
    val fuelType: String,
    val transmissionType: String,
    val engineAspiration: String,
    val engineLayout: String,
    val driveTrain: String,
    val files: List<MultipartFile>
)
