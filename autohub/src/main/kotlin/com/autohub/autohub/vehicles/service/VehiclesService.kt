package com.autohub.autohub.vehicles.service

import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.vehicles.dto.VehicleDto
import com.autohub.autohub.vehicles.entity.Vehicle
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

interface VehiclesService {
    fun allVehicles(pageNumber: Int): ResponseEntity<ApiResponse<List<Vehicle>>>
    fun addVehicle(dto: VehicleDto): ResponseEntity<ApiResponse<Vehicle>>
    fun vehicleById(string: String): ResponseEntity<ApiResponse<Vehicle>>
    fun updateVehicle(string: String, dto: VehicleDto): ResponseEntity<ApiResponse<Vehicle>>
    fun searchVehicles(string: String): ResponseEntity<ApiResponse<List<Vehicle>>>
    fun deleteVehicle(string: String): ResponseEntity<ApiResponse<String>>
}