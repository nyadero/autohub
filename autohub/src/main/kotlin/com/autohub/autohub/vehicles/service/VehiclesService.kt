package com.autohub.autohub.vehicles.service

import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.vehicles.dto.VehicleDto
import com.autohub.autohub.vehicles.entity.Vehicle
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

interface VehiclesService {
    fun allVehicles(pageNumber: Int): List<Vehicle>
    fun addVehicle(dto: VehicleDto): Vehicle
    fun vehicleById(string: String): Vehicle
    fun updateVehicle(string: String, dto: VehicleDto): Vehicle
    fun searchVehicles(string: String): List<Vehicle>
    fun deleteVehicle(string: String)
}