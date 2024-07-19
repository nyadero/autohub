package com.autohub.autohub.vehicles.service

import com.autohub.autohub.vehicles.dto.VehicleDto
import com.autohub.autohub.vehicles.entity.Vehicle
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

interface VehiclesService {
    fun allVehicles(pageNumber: Int): ResponseEntity<List<Vehicle>>
    fun addVehicle(dto: VehicleDto): ResponseEntity<Vehicle?>
    fun vehicleById(string: String): ResponseEntity<Vehicle>
    fun updateVehicle(string: String, dto: VehicleDto): ResponseEntity<Vehicle>
    fun searchVehicles(string: String): ResponseEntity<List<Vehicle>>
    fun deleteVehicle(string: String): ResponseEntity<String>
}