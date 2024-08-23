package com.autohub.autohub.vehicles.controller

import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.configuration.responses.Data
import com.autohub.autohub.configuration.responses.ResponseType
import com.autohub.autohub.vehicles.dto.VehicleDto
import com.autohub.autohub.vehicles.entity.Vehicle
import com.autohub.autohub.vehicles.service.VehiclesService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/vehicles")
class VehiclesController(
    private val vehiclesService: VehiclesService
) {

    // add vehicle
    @PostMapping("")
    fun addVehicle(
        @ModelAttribute vehicleDto: VehicleDto
    ): ResponseEntity<ApiResponse<Vehicle>>{
        val vehicle: Vehicle = vehiclesService.addVehicle(vehicleDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(Data(vehicle), "Vehicle added", ResponseType.Success)
        )
    }

    //    get all vehicles
    @GetMapping("")
    fun allVehicles(
        @RequestParam(name = "pageNumber", defaultValue = "0")  pageNumber: Int
    ): ResponseEntity<ApiResponse<List<Vehicle>>> {
        val vehicles: List<Vehicle> = vehiclesService.allVehicles(pageNumber)
        return ResponseEntity.status(HttpStatus.OK).body(
            ApiResponse(Data(vehicles), "Vehicles fetched", ResponseType.Success)
        )
    }

    // get vehicle by id
    @GetMapping("/{id}")
    fun vehicleById(
        @PathVariable("id") id: String
    ): ResponseEntity<ApiResponse<Vehicle>>{
        val vehicle: Vehicle = vehiclesService.vehicleById(id)
        return ResponseEntity.status(HttpStatus.OK).body(
            ApiResponse(Data(vehicle), "Vehicle fetched", ResponseType.Success)
        )
    }

    // update vehicle
    @PutMapping("/{id}")
    fun updateVehicle(
        @PathVariable("id") id: String,
        @RequestBody vehicleDto: VehicleDto
    ): ResponseEntity<ApiResponse<Vehicle>>{
        val vehicle: Vehicle = vehiclesService.updateVehicle(id, vehicleDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(Data(vehicle), "Vehicle updated", ResponseType.Success)
        )
    }

    // search vehicles
    @GetMapping("/search")
    fun searchVehicles(
        @RequestParam(name = "query") query: String
    ): ResponseEntity<ApiResponse<List<Vehicle>>>{
        val vehicles: List<Vehicle> = vehiclesService.searchVehicles(query)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(Data(vehicles), "Query returned", ResponseType.Success)
        )
    }

    // delete vehicle
    @DeleteMapping("/{id}")
    fun deleteVehicle(
        @PathVariable("id") id: String
    ): ResponseEntity<ApiResponse<Unit>>{
        vehiclesService.deleteVehicle(id)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(null, "Vehicle deleted", ResponseType.Success)
        )
    }

}